package fiuba.algo3.algocraft;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class Vector2DTest {

	@Test
	public void crearVectorNulo() 
	{
		new Vector2D(0,0);		
	}
	
	@Test
	public void crearVectorAleatorioEntreValores() 
	{
		Vector2D vector = new Vector2D().aleatorio(0, 100, 0, 712);
		
		Assert.assertTrue(vector.obtenerCoordenadaX() >= 0 && vector.obtenerCoordenadaX() <= 100);
		Assert.assertTrue(vector.obtenerCoordenadaY() >= 0 && vector.obtenerCoordenadaY() <= 712);
		
	}
	
	@Test
	public void distanciaEntreVectores() 
	{
		Vector2D origen = new Vector2D(0,0), destino = new Vector2D(321,718);
		
		Assert.assertTrue(origen.distanciaA(destino) == Math.sqrt(321*321+718*718));		
	}

}
