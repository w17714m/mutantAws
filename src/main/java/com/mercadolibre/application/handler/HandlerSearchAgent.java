package com.mercadolibre.application.handler;

import com.mercadolibre.application.command.CommandDragMoveType;
import com.mercadolibre.application.command.CommandPoint;
import com.mercadolibre.domain.excepcion.DnaException;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class HandlerSearchAgent {
    public static final int LENGTH_MUTANT = 4;
    private String[] dna;
    CommandDragMoveType dragMoveType;

    public HandlerSearchAgent(){}

    public boolean isAdn(){
        String values = "ATCG";
        String joinvalidation = Arrays.asList(dna).stream().collect(Collectors.joining());
        for (char character: joinvalidation.toCharArray()) {
            boolean match = false;
            for (char conjunto: values.toCharArray()){
                if(conjunto==character) match = true;
            }
            if(!match)
                return false;
        }

        /**
         * matrix out of index
         */
        if(dna.length!=6 || dna[0].length()!=6)
            return false;

        return true;
    }

    /**
     * Is mutant
     * @param dna array of dna
     * @return
     */

    public boolean isMutant(String[] dna) throws DnaException {
        this.dna = dna;
        if(!isAdn()) throw new DnaException("Dna no valido");
        this.dragMoveType = new CommandDragMoveType(dna[0].length()-1,dna.length-1);
        System.out.println(this.dragMoveType.toString());
        List<CommandPoint> searchDiagonalLeft = Arrays.asList( this.dragMoveType.getUp(),this.dragMoveType.getRight());
        List<CommandPoint> searchDiagonalRigh = Arrays.asList(this.dragMoveType.getUp(),this.dragMoveType.getLeft());
        List<CommandPoint> searchHorizontal = Arrays.asList(dragMoveType.getRight());
        List<CommandPoint> searchVertical = Arrays.asList(dragMoveType.getDown());

        ExecutorService executor = Executors.newFixedThreadPool( Runtime.getRuntime().availableProcessors());
        Collection<Callable<Boolean>> callables = new ArrayList<>();
        callables.add(
                        () -> this.initPatrol(
                            searchDiagonalLeft,
                            new CommandPoint(dragMoveType.getHeight() - LENGTH_MUTANT,0),
                            dragMoveType.getDownRight()
                            )
                    );
        callables.add(
                () -> this.initPatrol(
                        searchDiagonalRigh,
                        new CommandPoint(dragMoveType.getHeight()- LENGTH_MUTANT,dragMoveType.getWidth()),
                        dragMoveType.getDownLeft()
                )
        );
        callables.add(
                () -> this.initPatrol(
                        searchHorizontal,
                        new CommandPoint(0,0),
                        dragMoveType.getDown()
                )
        );
        callables.add(
                () -> this.initPatrol(
                        searchVertical,
                        new CommandPoint(0,0),
                        dragMoveType.getRight()
                )
        );
        boolean found = false;
        try {
            List<Future<Boolean>> result = executor.invokeAll(callables);
            for (Future f : result) {
                if((Boolean)f.get()){
                    found = true;
                    break;
                }

            }
        } catch (InterruptedException | ExecutionException e) {

        }

        executor.shutdownNow();
        return found;
    }

    /**
     * Find pattern within array dna
     * @param actualp
     * @param direction
     * @return
     */
    private boolean searchMutantGen(CommandPoint actualp, CommandPoint direction){
        CommandPoint actual = (CommandPoint) actualp.clone();
        int count = 1;
        char before = ' ';
        do{
            if(before!=' ')
                actual.drag(direction);
            if(before==this.dna[actual.getX()].toCharArray()[actual.getY()]){
                count++;
                if(count==LENGTH_MUTANT){
                    return true;
                }
            }else{
                count = 1;
            }
            before=this.dna[actual.getX()].toCharArray()[actual.getY()];
        }
        while(actual.canDrag(direction));
        return false;
    }

    /**
     * Algorith of searching generic
     * @param seekPath path of search
     * @param spawpoint spawn point of matrix
     * @param findDirection vector of search
     * @return is found
     */
    public boolean initPatrol(List<CommandPoint> seekPath, CommandPoint spawpoint, CommandPoint findDirection){

        CommandPoint actualPosition = spawpoint;
        boolean isFound = false;
        for(CommandPoint path: seekPath){
            CommandPoint temp = (CommandPoint) actualPosition.clone();
            do{
                if(!temp.equals(actualPosition))
                    actualPosition.drag(path);
                if(searchMutantGen(actualPosition,findDirection)){
                    isFound = true;
                    break;
                }
                temp = new CommandPoint(-1,-1);
            }while(actualPosition.canDrag(path));
            if(isFound) break;
        }
        return isFound;
    }

}
