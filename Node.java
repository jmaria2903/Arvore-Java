/*
 * Definindo a estrutura de um n� (Lista Encadeada)
 */
 
public class Node
{
	private Object dado; 	//Dado a ser armazenado no n�
	private Node prox;		//Refer�ncia para o pr�ximo n�
	
	//Devolve o conte�do do n�
	public Object getDado()
	{
		return dado;
	}
	
	//Atribui um valor ao conte�do do n�
	public void setDado(Object novovalor)
	{
		dado = novovalor;
	}
	
	//Devolve a refer�ncia do pr�ximo n�
	public Node getProx()
	{
		return prox;
	}
	
	//Atribui uma refer�ncia para o pr�ximo n�
	public void setProx(Node novoprox)
	{
		prox = novoprox;
	}

}

