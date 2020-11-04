package SistemaEnvios.Logica;

import SistemaEnvios.Dominio.Envio;

public class ListaEnvios {
	
	private NodoEnvio first;


	public ListaEnvios() {
		this.first = null;
	}


	public void IngresarEnvio(Envio envio) {
		NodoEnvio nn = new NodoEnvio(envio);
	    if (first ==null) {
		    first = nn;
		    first.setNext(first);
		    first.setPrevio(first);
	    
	    }else {	
	    	nn.setNext(first);
	        first.getPrevio().setNext(nn);
	        nn.setPrevio(first.getPrevio());
	        first.setPrevio(nn);
	        first = nn;
	    }
   
	}

	public Envio buscarEnvio(String codigo) {
	    NodoEnvio nn = first;
	    while(nn != nn.getPrevio() && !nn.getEnvio().getCodigo().equals(codigo)) {
	            nn = nn.getNext();

	    }
	    if(nn.getEnvio().getCodigo().equals(codigo)) {
	        return nn.getEnvio(); 

	    }
	    return null;
	}

    public NodoEnvio getFirst() {
        return first;
    }
    
    

    public int getCantidadElemento() {
    	int con = 0;
    	if(first!=null) {
	        NodoEnvio current = first.getNext();
	        while(current !=first) {
	        	con++;
	        	current=current.getNext();
	        }
	        con++;
	        return con;// have the first 
    	}
    	return con;
    	
    	
    	
        
    }


	    


}
