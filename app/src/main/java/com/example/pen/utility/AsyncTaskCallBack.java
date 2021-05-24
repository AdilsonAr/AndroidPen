package com.example.pen.utility;

/**
 * version: 1.0
 * ultima modificacion: 19/5/21
 * descripcion:
 * Una clase para ejecutar una tarea en un hilo
 * diferente. Cuando la tarea termina, se llama
 * a un metodo de callback para realizar alguna accion
 */
public class AsyncTaskCallBack {
    private static AsyncTaskCallBack instance;

    //region CONSTRCUTORES
    public static AsyncTaskCallBack getInstance(){
        if (instance == null){
            instance = new AsyncTaskCallBack();
        }
        return instance;
    }

    private AsyncTaskCallBack() {

    }
    //endregion

    /**
     * realiza la tarea y llama al callback en otro hilo
     */
    public void execute(Runnable task, Runnable callback){
        Thread th = new Thread(() ->{
            task.run();
            if(callback != null){
                callback.run();
            }
        });
        th.start();
    }
}
