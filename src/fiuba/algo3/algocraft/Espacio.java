package fiuba.algo3.algocraft;

public class Espacio extends Entidad{
	
	public Espacio(Espacio gas) 
	{
		super(gas);
	}

	public Espacio(Vector2D posicionEspacio, int dimension) 
	{
		super(posicionEspacio,dimension,dimension);			
	}	

}
