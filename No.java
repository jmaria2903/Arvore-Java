/* Classe que define um n� de uma �rvore bin�ria */

public class No
{
	//Dados do n�
	private Object dado;
	private No esq;
	private No dir;
	
	
	//Construtor com 3 par�metros 
	public No (Object novo, No _esq, No _dir )
	{ 
	   this.dado = novo;
	   this.esq = _esq; 
	   this.dir = _dir;
	}
	
	//Construtor sem par�metros
	public No ()
	{ 
		this( "", null, null ); 
	}

	//Construtor que s� recebe o valor da raiz
	public No(Object novo)
	{
		this(novo, null, null);
	}
	
	//Retorna o dado do n�
	public Object getDado()
	{ 
		return dado; 
	}
	
	//Retorna a sub�rvore Esquerda
	public No getEsq()
	{ 
		return esq; 
	}
	
	//Retorna s sub�rvore direita
	public No getDir()
	{ 
		return dir; 
	}
	
	//Altera o dado no n�
	public void setDado(Object novo)
	{ 
		this.dado = novo; 
	}
	
	//Altera a �rvore esquerda
	public void setEsq(No _esq) 
	{ 
		this.esq = _esq; 
	}
	
	//Altera a �rvore direita
	public void setDir(No _dir) 
	{ 
		this.dir = _dir; 
	}
}


