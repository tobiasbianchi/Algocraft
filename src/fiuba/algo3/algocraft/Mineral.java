package fiuba.algo3.algocraft;


public class Mineral extends Entidad {

	public Mineral(Mineral mineral) 
	{
		super(mineral);
	}

	public Mineral(Vector2D posicionMineral, int dimension) 
	{
		super(posicionMineral,dimension,dimension);			
	}

}
