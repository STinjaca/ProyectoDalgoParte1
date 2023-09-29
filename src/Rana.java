import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Rana {
	static private HashSet<String> ultimas = new HashSet<String>();

	public static void main(String[] args) {
		
		int veces = 3;
		
		String data = "ppprppppr";
		
		byte [] normalizada = normalizar(data);
		//int [] a = {0};
		
		System.out.println("a");
		mostarLista(normalizada);
		
		System.out.println("result");
		calculoRecursivo(normalizada,veces);
		
		System.out.println("Respuesta " + ultimas.size());
	}
	
	public static byte[] normalizar(String data) {
		byte []result = new byte [data.length()];
		
		for (int i = 0; i < data.length(); i++) {
			if(data.charAt(i) == 'r') {
				result[i] = 1;
			}
		}
		
		return result;
		
	}
	
	public static String mostarLista(int [] data) {
		String lista = "";
		for (int i = 0; i < data.length; i++) {
			lista += "" + data[i];
		}
		return lista;

	}
	public static String mostarLista(byte [] data) {
		String lista = "";
		for (int i = 0; i < data.length; i++) {
			lista += "" + data[i];
		}
		return lista;
		
	}
	
	public static void mostarMatriz(int [][] data) {
		
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.print(data[i][j]);
				if (j < (data[0].length -1)) {
					System.out.print("|");
				}
			}
			System.out.print("\n");
		}
		
		System.out.print("\n");

	}
	
	public static byte[][] calculoPosibilidades(byte[] data) {
		
		int[][] m = new int [data.length + 1][4];
		
		for (int i = 0; i < m.length ; i++) {
			for (byte d = 3; d > 1; d--) {
				if (i == 0 || d == 0) {
					m[i][d] = 0;
				} else if (i == 1 && d == 3) {
					m[i][d] = 0;
				} else if ( data[i-1] == 0 ) {
					m[i][d] = m[i-1][2];
				} else if (d == 3 && data[i-1] % 2 == 1 && data[i-2] == 0) {
					m[i][d] = (m[i-1][3] + 1)% 998244353;
				} else if (d == 3 && data[i-1] == 2) {
					m[i][d] = m[i-1][3];
				} else if (d == 2 && data[i-1] == 1) {
					m[i][d] = (m[i][3] + 1) % 998244353;
				} else if (d == 2 && data[i-1] == 2) {
					m[i][d] = (m[i-1][3] + 1) % 998244353;
				} else if (d == 2 && data[i-1] == 3) {
					m[i][d] = m[i][3];
				} else if (d == 3 && data[i-1] != 0 && data[i-1] != 0) {
					m[i][d] = m[i-1][3];
				}
			}
		}
		mostarMatriz(m);
		
		int maximos = m[m.length - 1][3];
		byte d = 3;
		byte[][] soluciones = new byte[maximos][data.length];
		int j = 0;
		int i = data.length;
		while (i > 0) {
			if (i == 0 || d == 0) {
				//m[i][d] = 0;
			} else if (i == 1 && d == 3) {
				i--;
				//m[i][d] = 0;
			} else if (data[i-1] == 0) {
				d = 2;
				i--;
				//m[i][d] = m[i-1][2];
			} else if (data[i-1] == 0) {
				
			} else if (d == 3 && data[i-1] % 2 == 1 && data[i-2] == 0) {
				soluciones[j] = data.clone();
				soluciones[j][i-2] = 3;
				soluciones[j][i-1] = 0;
				j++;
				i--;
				//m[i][d] = m[i-1][3] + 1;
			} else if (d == 3 && data[i-1] == 2) {
				i--;
				//m[i][d] = m[i-1][3];
			} else if (d == 2 && data[i-1] == 1) {
				soluciones[j] = data.clone();
				soluciones[j][i] = 2;
				soluciones[j][i-1] = 0;
				j++;
				d = 3;
				//m[i][d] = m[i][3] + 1;
			} else if (d == 2 && data[i-1] == 2) {
				soluciones[j] = data.clone();
				soluciones[j][i] = 2;
				soluciones[j][i-1] = 0;
				
				j++;
				i--;
				d = 3;
				//m[i][d] = m[i-1][3] + 1;
			} else if (d == 2 && data[i-1] == 3) {
				d = 3;
				//m[i][d] = m[i][3];
			} else if (d == 3 && data[i-1] != 0 && data[i-1] != 0) {
				i--;
				//m[i][d] = m[i-1][3];
			}
			
		}
		
		return soluciones;
		
	}
	
//	public static void calculoRecursivo(int[][] soluciones, int m) {
//		int totales = soluciones.length;
//		for (int i = 0; i < totales; i++) {
//			String llave = mostarLista(soluciones[i]);
//			if (!evaluadas.contains(llave)) {
//				//System.out.println("llave " + llave);
//				evaluadas.add(llave);
//				if(m > 0) {
//					int [][] solucionesSegunda = calculoPosibilidades(soluciones[i]);
//					//mostarMatriz(solucionesSegunda);
//					calculoRecursivo(solucionesSegunda, m-1);
//				}else {
//					ultimas.add(llave);
//				}
//				System.out.println(m);
//				
//			}
//		}
//	}

	public static int calculoRecursivo(byte[] data, int m) {
		HashMap<String, byte[][]> evalA = new HashMap<String, byte[][]> ();
		HashMap<String, byte[][]> evalB = new HashMap<String, byte[][]> ();
		String nombre;
		if (m%2 == 1) {
			evalA.put(mostarLista(data),calculoPosibilidades(data));	
		} else {
			evalB.put(mostarLista(data),calculoPosibilidades(data));	
		}
		int n = m;
		while (m > 0) {
			if (m%2 == 1) {
				evalB = new HashMap<String, byte[][]> ();
				System.out.println("Tamano de A " +evalA.size());
				for ( byte[][] soluciones : evalA.values()) {
					for (int i = 0; i < soluciones.length; i++) {
						nombre = mostarLista(soluciones[i]);
						if (!evalB.containsKey(nombre)) {
							evalB.put(nombre,calculoPosibilidades(soluciones[i]));	
						}
					}
				}
			}else{
				evalA = new HashMap<String, byte[][]> ();
				System.out.println("Tamano de B " + evalB.size());
				for ( byte[][] soluciones : evalB.values()) {
					for (int i = 0; i < soluciones.length; i++) {
						nombre = mostarLista(soluciones[i]);
						if (!evalA.containsKey(nombre)) {
							evalA.put(nombre,calculoPosibilidades(soluciones[i]));	
						}
					}
				}
				
			}
			
			
			m--;
		}
		
		if (n%2 == 1) {
			for ( byte[][] soluciones : evalA.values()) {
				for (int i = 0; i < soluciones.length; i++) {
					nombre = mostarLista(soluciones[i]);
					ultimas.add(nombre);
				}
			}
		}else{
			for ( byte[][] soluciones : evalB.values()) {
				for (int i = 0; i < soluciones.length; i++) {
					nombre = mostarLista(soluciones[i]);
					ultimas.add(nombre);
				}
			}
			
		}
		return ultimas.size();
		
	}

	
	

}
