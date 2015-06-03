package fiuba.algo3.algocraft;

public class Vector2D {
	
	private double x;
	private double y;
	private double norma;

	public Vector2D(double posicionX, double posicionY) 
	{
		if(posicionX == 0 && posicionY == 0)
		{
			x = 0;
			y = 0;
			norma = 0;
		}
		else
		{
			norma = Math.sqrt(posicionX*posicionX+posicionY*posicionY);
			x = posicionX/norma;
			y = posicionY/norma;
		}
	}

	public Vector2D(Vector2D vector) 
	{
		x = vector.x;
		y = vector.y;
		norma = vector.norma;
	}

	public Vector2D() 
	{
		x = 0;
		y = 0;
		norma = 0;
	}

	public double distanciaA(Vector2D posicion) 
	{
		return Math.sqrt(Math.pow(posicion.obtenerCoordenadaX()-x*norma, 2) + Math.pow(posicion.obtenerCoordenadaY()-y*norma,2));		
	}

	public double obtenerCoordenadaX() 
	{
		return x*norma;
	}

	public double obtenerCoordenadaY() 
	{
		return y*norma;
	}

	public Vector2D aleatorio(double limiteInferiorX, double limiteSuperiorX, double limiteInferiorY, double limiteSuperiorY) 
	{
		x = Math.random()*(limiteSuperiorX-limiteInferiorX)+limiteInferiorX;
		y = Math.random()*(limiteSuperiorY-limiteInferiorY)+limiteInferiorY;
		
		if(x==0 && y==0)
			norma = 0;
		else
		{
			norma = Math.sqrt(x*x+y*y);
			x /= norma;
			y /= norma;		
		}
		
		return this;
	}
	
	public boolean distintoA(Vector2D vector)
	{
		if(x != vector.x && y != vector.y && norma != vector.norma)
			return true;
		return false;
		
	}
}
