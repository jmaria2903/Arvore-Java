/*
 * Implementação de uma Fila Dinâmica com uma lista simplesmente encadeada
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
 		Node novo = new Node();		//Cria um novo nó
 		novo.setDado(x);			//Coloca o dado dentro do nó
 		novo.setProx(null);			//Como esse será o último,não tem próximo
 		if(QIsEmpty())
 		{
 			comeco = novo;			//Como estava vazia, esse nóe será começo e fim
 			fim=  comeco;
 		}
 		else
 		{
 			fim.setProx(novo);		//Depois do fim da fila atual, o novo nó
 			fim = novo;				//O novo fim é o novo nó
 		}
 		total++;					//Incrementa o número de elementos
 	}
 	
 	public Object Dequeue()
 	{
 		Object resp = null;
 		if(!QIsEmpty())
 		{
 			resp = comeco.getDado();		//Captura o dado do começo da Fila
 			comeco = comeco.getProx();		//O começo anda para o próximo
 			total--;						//Decrementa número de elementos
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

