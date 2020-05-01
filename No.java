/* Classe que define um nó de uma árvore binária */

public class No
{
	//Dados do nó
	private Object dado;
	private No esq;
	private No dir;
	
	
	//Construtor com 3 parâmetros 
	public No (Object novo, No _esq, No _dir )
	{ 
	   this.dado = novo;
	   this.esq = _esq; 
	   this.dir = _dir;
	}
	
	//Construtor sem parâmetros
	public No ()
	{ 
		this( "", null, null ); 
	}

	//Construtor que só recebe o valor da raiz
	public No(Object novo)
	{
		this(novo, null, null);
	}
	
	//Retorna o dado do nó
	public Object getDado()
	{ 
		return dado; 
	}
	
	//Retorna a subárvore Esquerda
	public No getEsq()
	{ 
		return esq; 
	}
	
	//Retorna s subárvore direita
	public No getDir()
	{ 
		return dir; 
	}
	
	//Altera o dado no nó
	public void setDado(Object novo)
	{ 
		this.dado = novo; 
	}
	
	//Altera a árvore esquerda
	public void setEsq(No _esq) 
	{ 
		this.esq = _esq; 
	}
	
	//Altera a árvore direita
	public void setDir(No _dir) 
	{ 
		this.dir = _dir; 
	}
}


