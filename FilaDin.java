/*
 * Implementa��o de uma Fila Din�mica com uma lista simplesmente encadeada
 */
 
 import javax.swing.*;
 
 public class FilaDin
 {
 	Node comeco;			//Comeco da Fila
 	Node fim;				//Fim da Fila
 	int total;				//Contador de elementos
 	
 	public void QInit()
 	{
 		comeco = null;
 		fim = null;
 		total = 0; 		
 	}	
 	
 	public boolean QIsEmpty()
 	{
 		if(total==0)
 			return true;
 		else
 			return false;
 	}
 	
 	public void Enqueue(Object x)
 	{
 		Node novo = new Node();		//Cria um novo n�
 		novo.setDado(x);			//Coloca o dado dentro do n�
 		novo.setProx(null);			//Como esse ser� o �ltimo,n�o tem pr�ximo
 		if(QIsEmpty())
 		{
 			comeco = novo;			//Como estava vazia, esse n�e ser� come�o e fim
 			fim=  comeco;
 		}
 		else
 		{
 			fim.setProx(novo);		//Depois do fim da fila atual, o novo n�
 			fim = novo;				//O novo fim � o novo n�
 		}
 		total++;					//Incrementa o n�mero de elementos
 	}
 	
 	public Object Dequeue()
 	{
 		Object resp = null;
 		if(!QIsEmpty())
 		{
 			resp = comeco.getDado();		//Captura o dado do come�o da Fila
 			comeco = comeco.getProx();		//O come�o anda para o pr�ximo
 			total--;						//Decrementa n�mero de elementos
 			return resp;
 		}
 		return resp;
 	}
 	
 	public void Show()
 	{
 		if(QIsEmpty())
 			JOptionPane.showMessageDialog(null,"Fila Vazia!");
 		else
 		{
	 		Node aux;
	 		String saida = "";
	 		aux = comeco;
	 		while(aux!=null)
	 		{
	 			saida += aux.getDado().toString() + ", ";
	 			aux = aux.getProx();
	 		}
	 		JOptionPane.showMessageDialog(null,"F:[ "+saida+"]");
	 	}
	 }
 }

