package edu.lenin.domain.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NewInterface extends Remote {
    // Trae todas la noticias
    public String getAllNews() throws RemoteException;

    // Crea una noticia
    public String createNew(String id, String titular, String fechaCreacion, String fechaActualizacion, String autor, String contenido ) throws RemoteException;
    // Modifica una noticia se pasa y ....información a modificar
    public String modifyNew(String id) throws RemoteException;
    // Elimina una noticia
    public String deleteNews(String id) throws RemoteException;
    //Por Titular, por Autor o por contenido, crear filtro para buscar noticias
    public String searchNews(String parametro) throws RemoteException;

}
