package fiuba.algo3.algocraft;

public abstract class Entidad {
	
	private Vector2D posicion;
	private int dimensionX;
	private int dimensionY;
	
	public boolean incluyeA(Vector2D posicion)
	{		
		
		if( this.posicion.obtenerCoordenadaX() <= posicion.obtenerCoordenadaX() && posicion.obtenerCoordenadaX() <= (this.posicion.obtenerCoordenadaX()+dimensionX))
			if( this.posicion.obtenerCoordenadaY() <= posicion.obtenerCoordenadaY() && posicion.obtenerCoordenadaY() <= (this.posicion.obtenerCoordenadaY()+dimensionY))
				return true;
		return false;
	}

	public Vector2D obtenerPosicion() {
		return new Vector2D(posicion);
	}
	
	public Entidad(Vector2D posicion, int dimensionX, int dimensionY)
	{
		this.posicion = new Vector2D(posicion);
		this.dimensionX = dimensionX;
		this.dimensionY = dimensionY;
	}

	public Entidad(Entidad entidad) 
	{
		posicion = new Vector2D(entidad.posicion);
		dimensionX = entidad.dimensionX;
		dimensionY = entidad.dimensionY;		
	}
}
