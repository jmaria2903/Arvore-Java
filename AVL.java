/* Implementa uma árvore AVL */

public class AVL
{
	private NoAVL raiz;				//Raiz da árvore
	private int altura;				//Altura da árvore
	private int nElem;				//Número de nós da árvore
	private boolean flagInsercao;	//Verifica se já foi feita a inserção
	private boolean flagRemove;		//Verifica se já foi feita a remoção
	public String Said = "";		 
	public String Arvore[]= new String[15]; 	
	
	public  String r = "@";					
	
	public String ld0 = "@";
	public String le0 = "@";	
	
	public String ld10 = "@", ld11 = "@";
	public String le10 = "@", le11 = "@";
	
	public String ld20 = "@", ld21 = "@",ld22 = "@", ld23 = "@";
	public String le20 = "@", le21 = "@",le22 = "@", le23 = "@";		
		
	public AVL(Object dado)	
	{
		raiz = new NoAVL(dado);
		altura = 0;
		nElem = 1;
	}

	public AVL() 
	{
		raiz = null;
		altura = -1;
		nElem = 0;
	}

	public NoAVL getRaiz() {
		return raiz;
	}

	public void setRaiz(NoAVL _raiz){
		raiz = _raiz;
	}

	public int getAltura() 
	{
		return altura;
	}

	public void setAltura(int _altura) {
		altura = _altura;
	}

	public int getNElem() {
		return nElem;
	}

	public void setNElem(int _nElem) {
		nElem = _nElem;
	}

	public boolean isEmpty() {
		if(raiz == null)
			return true;
		else
			return false;
	}

	//Compara 2 Objects como se fossem Strings
	//Se retornar >0, primeiro maior
	//Se retornar <0, primeiro menor
	//Se retornar =0, são iguais
	//private int compara(Object ob1, Object ob2)
	public int compara(Object ob1, Object ob2)
	{
		String s1 = ob1.toString().toLowerCase();
		String s2 = ob2.toString().toLowerCase();
		
		return s1.compareTo(s2);
			
	}

	private NoAVL searchNoAVL (NoAVL raiz, Object e) {
		//Se a raiz estiver nula, o elemento não existe
		if( raiz == null )
			return null;
		else
			//Elemento encontrado na raiz
			if( compara(e, raiz.getDado() ) == 0)
				return raiz;
			else
				//Continue procurando recursivamente
				if( compara(e, raiz.getDado() ) < 0)
					return searchNoAVL (raiz.getEsq(), e );
				else
					return searchNoAVL (raiz.getDir(), e );
	}

	public NoAVL searchAVL(Object e){
		return searchNoAVL( raiz,e );
	}

	//Rotação Simples para a Direita
	private NoAVL rotacaoSD (NoAVL A){
		NoAVL B = A.getEsq();

		//Se não for a raiz, tem um pai
		if( A.getPai() != null ) 
			//Se A for o filho esquerdo, o pai assume como filho esquerdo o B
			if( A.getPai().getEsq() == A )
				A.getPai().setEsq(B);
			//Senão o pai assume como filho direito o B
			else
				A.getPai().setDir(B);

		//O pai de B agora é o pai de A
		B.setPai( A.getPai());
		
		//Como o B sumiu, pode ter deixado um órfão que quem assume é o A
		A.setEsq( B.getDir());

		//Se assumiu o filho, setar o pai dele sendo o A
		if( A.getEsq() != null )
			A.getEsq().setPai( A );

		//B passa a ser pai de A e A filho de B
		B.setDir( A );
		A.setPai( B );

		return B;

	}

	//Rotação Simples para a Esquerda
	private NoAVL rotacaoSE (NoAVL A) {
		NoAVL B = A.getDir();

		//Se não for a raiz, tem um pai
		if( A.getPai() != null )
			//Se A for o filho esquerdo, o pai assume como filho esquerdo o B
			if( A.getPai().getDir() == A )
				A.getPai().setDir(B);
			//Senão o pai assume como filho direito o B
			else
				A.getPai().setEsq(B);

		//O pai de B agora é o pai de A
		B.setPai( A.getPai());

		//Como o B sumiu, pode ter deixado um órfão que quem assume é o A
		A.setDir( B.getEsq());

		//Se assumiu o filho, setar o pai dele sendo o A
		if( A.getDir() != null )
			A.getDir().setPai( A );

		//B passa a ser pai de A e A filho de B
		B.setEsq( A );
		A.setPai( B );

		return B;

	}

	//Rotação dupla para a direita
	private NoAVL rotacaoDD (NoAVL A) {
		rotacaoSE ( A.getEsq() );
		return ( rotacaoSD(A) );
	}

	//Rotação dupla para a esquerda
	private NoAVL rotacaoDE(NoAVL A) {
		rotacaoSD( A.getDir() );
		return ( rotacaoSE(A) );
	}

	//Insere um item na árvore a partir da raiz (método público)
	public void insereAVL (Object k)
	{
		flagInsercao = false;
		setRaiz(insereNoAVL(raiz,k));
		setNElem(getNElem()+1);
	}

	//Método que faz a inserção
	private NoAVL insereNoAVL(NoAVL raiz, Object x)
	{
		//Se o nó não estiver nulo
		if(raiz != null)
		{
			//Se o elemento for menor que o nó atual
			if(compara(x, raiz.getDado() ) < 0)
			{
				//Insere recursivamente à esquerda
				raiz.setEsq( insereNoAVL( raiz.getEsq(), x ) );
				raiz.getEsq().setPai(raiz);
				//Se já inseriu
				if(flagInsercao)
				{
					switch(raiz.getFb())
					{
						case 1: //Caso ele tinha 1 filho direito, o filho esquerdo balanceou
							raiz.setFb(0);
							flagInsercao = false;
							break;
						case 0: //Caso não tinha filhos, agora tem só o esquerdo
							raiz.setFb(-1);
							break;
						case -1: //Caso já tinha um filho esquerdo, tem que rotacionar

							//Se o filho esquerdo só tinha um filho esquerdo, então é rotação simples para a direita
							if(raiz.getEsq().getFb()==-1)
							{
								raiz = rotacaoSD(raiz);
								raiz.setFb(0);
								raiz.getDir().setFb(0);
							}
							//Caso contrário a rotação é dupla para a direita
							else
							{
								raiz = rotacaoDD(raiz);
								if(raiz.getFb()==0)
								{ //1º caso
									raiz.getDir().setFb(0);
									raiz.getEsq().setFb(0);
								}
								else if(raiz.getFb()==-1)
								{ //2º caso
									raiz.getDir().setFb(1);
									raiz.getEsq().setFb(0);
								}
								else
								{ //3º caso
									raiz.getDir().setFb(0);
									raiz.getEsq().setFb(-1);
								}
								raiz.setFb(0);
							}
							flagInsercao = false;
							break;
					}
				}
			}
			else
			{
				//Insere Recursivamente à direita
				raiz.setDir(insereNoAVL(raiz.getDir(),x));
				raiz.getDir().setPai(raiz);
				//Se já inseriu
				if(flagInsercao)
				{
					switch(raiz.getFb())
					{
						case 0: //Se não tinha filhos, agora tem só o direito
							raiz.setFb(1);
							break;
						case -1: //Se só tinha um esquerdo, equilibrou
							raiz.setFb(0);
							flagInsercao = false;
							break;
						case 1: //Se já tinha filhos direito, tem que rotacionar
							//Se o filho direito tiver apenas um filho direito, então é rotação simples para a esquerda
							if(raiz.getDir().getFb()==1)
							{
								raiz = rotacaoSE(raiz);
								raiz.setFb(0);
								raiz.getEsq().setFb(0);
							}
							//Caso contrário, rotação dupla para a esquerda
							else
							{
								raiz = rotacaoDE(raiz);
								if(raiz.getFb()==0)
								{ //1º caso
									raiz.getDir().setFb(0);
									raiz.getEsq().setFb(0);
								}
								else if(raiz.getFb()==1)
								{ //2º caso
									raiz.getDir().setFb(0);
									raiz.getEsq().setFb(-1);
								}
								else
								{ //3º caso
									raiz.getDir().setFb(1);
									raiz.getEsq().setFb(0);
								}
								raiz.setFb(0);
							}
							flagInsercao = false;
							break;
					}
				}
			}
		}
		//Se chegar depois da folha(raiz==null) criar nó
		else
		{
			//Quando chegar na folha, inserir novo No e trocar p flag para passar pelo processo de rotação
			raiz = new NoAVL(x);
			flagInsercao = true;
		}

		return raiz;
	}

	//Remove uma Object k da árvore AVl (método público)
	public boolean removeAVL(Object k)
	{
		flagRemove = false;
		if(isEmpty())
		{
			Said += "Erro ao remover, árvore AVL está vazia!";
			return false;
		}
		else if(searchAVL(k)==null)
		{
			Said += "Erro ao remover, elemento não existe na árvore!";
			return false;
		}
		else
		{
			raiz = removeNoAVL(raiz, k);
			setNElem(getNElem()-1);
			return true;
		}
	}

	//Método privado recursivo
	public NoAVL removeNoAVL(NoAVL raiz, Object x)
	{
		//Se o elemento for menor que a raiz, chamar recursivamente para o lado esquerdo
		if(compara(x, raiz.getDado()) < 0)
		{
			raiz.setEsq(removeNoAVL(raiz.getEsq(),x));

			//Se já removeu, relabancear
			if(flagRemove)
				raiz = balanceamentoEsquerdo(raiz);
		}
		//Se o elemento for maior que a raiz, chamar recursivamente para o lado direito
		else if(compara(x, raiz.getDado()) > 0)
		{
			raiz.setDir(removeNoAVL(raiz.getDir(),x));

			//Se já removeu, relabancear
			if(flagRemove)
				raiz = balanceamentoDireito(raiz);
		}
		//Se o elemento a remover está na raiz (encontrou o nó)
		else
		{
			//Se não tiver um filho direito
			if(raiz.getDir()==null)
			{
				//Se tiver o filho esquerdo (assume o lugar do pai)
				if(raiz.getEsq()!=null)
					raiz.getEsq().setPai(raiz.getPai());
				//Filho esquerdo sobe
				raiz = raiz.getEsq();
				flagRemove = true;
			}
			//Se não tiver um filho esquerdo
			else if(raiz.getEsq()==null)
			{
				//Se tiver o filho direito (assume o lugar do pai)
				if(raiz.getDir()!=null)
					raiz.getDir().setPai(raiz.getPai());
				//Filho direito sobe
				raiz = raiz.getDir();
				flagRemove = true;
			}
			//Tem os dois filhos, calcular o GetMax
			else
			{
				raiz.setEsq(buscaRemove(raiz.getEsq(),raiz));
				//Se necessário efetuar balanceamento esquerdo, pois a remoção foi à esquerda
				if(flagRemove)
					raiz = balanceamentoEsquerdo(raiz);
			}
		}
		return raiz;
	}

	//Reorganiza os fatores de balanceamento na remoção
	private NoAVL balanceamentoEsquerdo(NoAVL no)
	{
		switch(no.getFb())
		{
			case -1: //Se tinha um nó esquerdo, removeu e balanceou
				no.setFb(0);
				break;
			case 0:  //Se não tinha filhos, ficou com um à direita
				no.setFb(1);
				break;
			case 1:  //Se tinha 1 nível a mais à direita, Alanceou
				NoAVL subDir = no.getDir();
				int fb = subDir.getFb();
				if(fb>=0)
				{
					subDir = rotacaoSE(no);
					if(fb==0)
					{
						no.setFb(1);
						subDir.setFb(-1);
						flagRemove = false;
					}
					else
					{
						no.setFb(0);
						subDir.setFb(0);
					}
					no = subDir;
				}
				else
				{
					no = rotacaoDD(no);
					if(no.getFb()==0)
					{
						no.getDir().setFb(0);
						no.getEsq().setFb(0);
					}
					else if(no.getFb()==1)
					{
						no.setFb(0);
						no.getDir().setFb(0);
						no.getEsq().setFb(-1);
					}
					else
					{
						no.setFb(0);
						no.getDir().setFb(1);
						no.getEsq().setFb(0);
					}
				}
		}
		return no;
	}

	//Reorganiza os fatores de balanceamento na remoção
	private NoAVL balanceamentoDireito(NoAVL no)
	{
		switch(no.getFb())
		{
			case 1: //Se tinha um nó direito, removeu e balanceou
				no.setFb(0);
				break;
			case 0:  //Se não tinha filhos, ficou com um à esquerda
				no.setFb(-1);
				flagRemove = false;
				break;
			case -1:  //Se tinha 1 nível a mais à direita, Alanceou
				NoAVL subEsq = no.getEsq();
				int fb = subEsq.getFb();
				if(fb<=0)
				{
					subEsq = rotacaoSD(no);
					if(fb==0)
					{
						no.setFb(-1);
						subEsq.setFb(1);
						flagRemove = false;
					}
					else
					{
						no.setFb(0);
						subEsq.setFb(0);
					}
					no = subEsq;
				}
				else
				{
					no = rotacaoDE(no);
					if(no.getFb()==0)
					{
						no.getDir().setFb(0);
						no.getEsq().setFb(0);
					}
					else if(no.getFb()==-1)
					{
						no.setFb(0);
						no.getDir().setFb(1);
						no.getEsq().setFb(0);
					}
					else
					{
						no.setFb(0);
						no.getDir().setFb(0);
						no.getEsq().setFb(-1);
					}
				}
		}
		return no;
	}

	//Busca o maior valor da subárvore esquerda para substituir o nó excluído
	private NoAVL buscaRemove(NoAVL raiz, NoAVL noChave)
	{
		NoAVL noRemovido;
		if(raiz.getDir()!=null)
		{
			raiz.setDir(buscaRemove(raiz.getDir(),noChave));
			if(flagRemove)
				raiz = balanceamentoDireito(raiz);
		}
		else
		{
			//Altera o valor da chave
			noChave.setDado(raiz.getDado());
			noRemovido = raiz;
			//Se nó direito	com maior valor tem subárvore esquerda deve ser removido
			raiz = raiz.getEsq();
			if(raiz!=null)
				raiz.setPai(noRemovido.getPai());
			flagRemove = true;
			noRemovido = null;
		}
		return raiz;
	}

  //Atravessamento em ordem
  public void emOrdem()
  {
	emOrdemAux(raiz);
  }
  
  private void emOrdemAux( NoAVL raiz )
  {
    if ( raiz != null )
    {
      emOrdemAux( raiz.getEsq() );
      Said += raiz.getDado() + ", "; 
      emOrdemAux( raiz.getDir() );
    }
  }

  //Atravessamento pré Ordem
  public void preOrdem()
  {
	preOrdemAux(raiz);
  }
  
  private void preOrdemAux( NoAVL raiz )
  {
    if ( raiz != null )
    {
      Said +=  raiz.getDado() + ", ";
      preOrdemAux( raiz.getEsq() );
      preOrdemAux( raiz.getDir() );
    }
  }

  //Atravessamento pós ordem
  public void posOrdem()
  {
	posOrdemAux(raiz);
  }
  
  private void posOrdemAux( NoAVL raiz )
  {
    if ( raiz != null )
    {
      posOrdemAux( raiz.getEsq() );
      posOrdemAux( raiz.getDir() );
      Said +=  raiz.getDado() + ", ";
    }
  }

  	//Atravessamento em nível
	public void emNivel()
	{  
		int i = 0;		
		NoAVL noAux;
		FilaDin f;
		if (!isEmpty())
		{
			f = new FilaDin();
			f.Enqueue( raiz );
			while( !f.QIsEmpty() )
			{
				noAux = (NoAVL) f.Dequeue ();
				if (noAux.getEsq() != null)
				{
					f.Enqueue( noAux.getEsq());          		
				}
				if (noAux.getDir() != null)
				{
					f.Enqueue( noAux.getDir() );				
				}
				Said +=  noAux.getDado() + ", ";
				if (i < 15)
				{
					Arvore[i] = noAux.getDado()+"";
					i++;
				}				
			}
		}
  	}
  	//**************************
  	public void  VerEsq( String  x )  
	{  
		stopEsq:
		{
			//*********> nivel 1 <*********    
		    if (le0 == "@")
		    {
		        le0 = x;
		        break stopEsq;
		    }
		    //*********> nivel 2 <*********    
		    if (le10 == "@")
		    {
		        if(compara(le0+"", x+"") > 0)        
		        {
		            le10 = x;
		            break stopEsq;
		        }
		    }
		    if (le10 != "@" &&   compara(x+"",le0+"") < 0)
		    {
		        if(compara(le10+"",x+"") > 0)
		        {
		            le20 = x;
		        }
		        else
		        {
		            le21 = x;        
		        }
		        break stopEsq;
		    }
		    
		    if (le11 == "@")
		    {
		        if(compara(le0+"", x+"") <= 0)        
		        {
		            le11 = x;
		            break stopEsq;
		        }
		    }
		    
		    if (le11 != "@" &&   compara(x+"",le0+"") > 0)
		    {
		        if(compara(le11+"",x+"") > 0)
		        {
		            le22 = x;
		        }
		        else
		        {    
		            le23 = x;
		        }
		        break stopEsq;  	
		    }
		    //*********> nivel 3 <*********    
		    if (le20 == "@")
		    {
		        if(compara(le10+"",x+"") > 0)
		        {
		            le20 = x;
		            break stopEsq; 
		        }
		    }
		    
		    if (le21 == "@")
		    {
		        if(compara(le10+"",x+"") <= 0)
		        {
		        	le21 = x;
		        	break stopEsq;
		        }
		    }
		    
		    if (le22 == "@")
		    {
		    	if(compara(le11+"",x+"") > 0)
		        {
		            le22 = x;
		        	break stopEsq;
		        }
		    }
		    
		    if (le22 == "@")
		    {
		        if(compara(le11+"",x+"") <= 0)
		        {
		        	le23 = x;
		        	break stopEsq;
		        }
		    }		    
	    }
	}    

	public void  verDir( String  x )  
	{
        stopDir:
        {
           //*********> nivel 1 <*********    
		    if (ld0 == "@")
		    {
		        ld0 = x;
		        break stopDir;
		    }
		    //*********> nivel 2 <*********    
		    if (ld10 == "@")
		    {
		        if(compara(ld0+"", x+"") > 0)        
		        {
		            ld10 = x;
		            break stopDir;
		        }
		    }
		    if (ld10 != "@" &&   compara(x+"",ld0+"") < 0)
		    {		    	
		        if(compara(ld10+"",x+"") > 0)
		        {
		            ld20 = x;		            
		        }
		        else
		        {
		            ld21 = x;        
		        }
		        break stopDir;
		    }
		    
		    if (ld11 == "@")
		    {
		        if(compara(ld0+"", x+"") <= 0)        
		        {
		            ld11 = x;
		            break stopDir;
		        }
		    }
		    
		    if (ld11 != "@" &&   compara(x+"",ld0+"") > 0)
		    {
		        if(compara(ld11+"",x+"") > 0)
		        {
		            ld22 = x;
		        }
		        else
		        {    
		            ld23 = x;
		        }
		        break stopDir;  	
		    }
		    //*********> nivel 3 <*********    
		    if (ld20 == "@")
		    {
		        if(compara(ld10+"",x+"") > 0)
		        {
		            ld20 = x;
		            break stopDir; 
		        }
		    }
		    
		    if (ld21 == "@")
		    {
		        if(compara(ld10+"",x+"") <= 0)
		        {
		        	ld21 = x;
		        	break stopDir;
		        }
		    }
		    
		    if (ld22 == "@")
		    {
		    	if(compara(ld11+"",x+"") > 0)
		        {
		            ld22 = x;
		        	break stopDir;
		        }
		    }
		    
		    if (ld22 == "@")
		    {
		        if(compara(ld11+"",x+"") <= 0)
		        {
		        	ld23 = x;
		        	break stopDir;
		        }
		    }		    		    
        }
    }
	public String InicString() 
	{				
		le0   = "@";
		ld0   = "@";		
		
		ld10  = "@";
		ld11  = "@";
		
		le10  = "@";
		le11  = "@";	
		
		ld20  = "@";
		ld21  = "@";
		ld22  = "@";
		ld23  = "@";
		
		le20  = "@";
		le21  = "@";
		le22  = "@";
		le23  = "@";		
		r     = "@";				
		for (int i = 0; i < 15; i++)		
		{					
			Arvore[i] = "@";				
		}
		return "";
	}
  	//****************************
}