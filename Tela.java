/**********************************************/
/*Trabalho de Estrutura de Dados - 28/10/2005 */	
/*Nomes						RGMs	          */
/*Jose Maria Gomes	        41.000-7          */
/**********************************************/

import java.awt.*;
import java.awt.Component.*;
import java.awt.event.*;
import javax.swing.*;

public class Tela extends JFrame
{		
	AVL a = new AVL();
	
	private JTextField JTxtDado; //===========================> Define JText
	private JLabel JLblDado, JLblOpcao, JLblTpImp,JLblmsg;//==> Define JLabel
	private JComboBox JCmbTiImp,JCmbopcao;//==================> Define JCamboBox
	private String Itens[]     = {"Em Ordem ","P�s-Ordem", "Pr�-Ordem", "Em N�vel "};//=> Inseri valores para o Combo Box	
	private String Itensopc [] = {"Imprimir ","Incluir  ", "Remover  "};//==============> Inseri valores para o Combo Box	
	private JTextArea textArea;  
	private JButton Enter;  		
		
	public Tela()	
	{	
		super("Trabalho de Estrutura de Dados");			
		Container  container = getContentPane();
		container.setLayout (new FlowLayout());		
	
		//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
		//=================> Linha de Op��es <=================
		JLblOpcao = new JLabel("Informe o Opcao   ");//"Informe o Opcao");
		container.add(JLblOpcao);		
		JCmbopcao = new JComboBox(Itensopc);
		JCmbopcao.setMaximumRowCount(3);		
		container.add(JCmbopcao);
		
		//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
		//=================> Linha de Tipo de Impress�o <======		                       
		JLblTpImp = new JLabel("Inf.Tipo impressao");
		container.add(JLblTpImp);				
		JCmbTiImp = new JComboBox(Itens);
		JCmbTiImp.setMaximumRowCount(3);		
		container.add(JCmbTiImp);
		
		//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
		//=================> Linha de Incluis�o/Exclus�o de dados <======		                      
		JLblDado = new JLabel("Digite o valor    ");
		container.add(JLblDado);		
		JTxtDado = new JTextField (10);		
		container.add(JTxtDado);				
		
		//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
		//=================> Linha de Saida de dados <=========
		textArea = new JTextArea(2,20);						
		textArea.setText("");
		textArea.setText(a.Said);
		textArea.setEnabled(false);
		getContentPane().add(textArea);		
	   	
	   	//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
		//=================> Linha do Bot�o enter <============
	   	Enter = new JButton("Enter");	   	
		container.add(Enter); 								
		Enter.addActionListener//==> Aciona Bot�o
		(	
        	new ActionListener() 
        	{           
        		public void actionPerformed( ActionEvent event )
            	{ 	
            		JLblmsg.setText("");				
					if  (JCmbopcao.getSelectedIndex() == 0) //=>Impress�o
					{						
						if  (JCmbTiImp.getSelectedIndex() == 0)
						{
							a.Said = "";
							a.emOrdem();							
							if  (a.Said == "")
							{
								JLblmsg.setText("* Dados n�o localizados");						
							}
							else
							{
								textArea.setText(a.Said);								
							}																																
						}												
						else
						if (JCmbTiImp.getSelectedIndex() == 1)
						{
							a.Said = "";
							a.posOrdem();
							textArea.setText(a.Said);
						}
						else	
						if  (JCmbTiImp.getSelectedIndex() == 2)
						{
							a.Said = "";
							a.preOrdem();
							textArea.setText(a.Said);
						}
						else
						if  (JCmbTiImp.getSelectedIndex() == 3)
						{
							a.Said = "";
							a.emNivel();
							textArea.setText(a.Said);
						}												
					}				
					else
					if  (JCmbopcao.getSelectedIndex() == 1) //=>Inclus�o
					{	
						a.insereAVL (JTxtDado.getText());
						JTxtDado.setText("");
						repaint();												
					}
					else						
					if  (JCmbopcao.getSelectedIndex() == 2)//=>Exclus�o
					{													
						if (a.removeAVL(JTxtDado.getText())== true)							
						{
							JLblmsg.setText("* Elemento excluido com Sucesso");								
							a.Said = "";
							a.emNivel();
							textArea.setText(a.Said);
							repaint();
						}	
						else
						{
							JLblmsg.setText("* Elemento n�o encontrado");
						}
						JTxtDado.setText("");																		
            		}
         		}
         		
         	}
      	);      	
      	
      	JLblmsg = new JLabel("");
		container.add(JLblmsg);				
		JLblmsg.setForeground(Color.red);		
		setSize(350,550);
		setLocation(250,10);
		setVisible(true);			
		
	}	
	
	public void paint( Graphics g )
   	{      	
      	super.paint(g);	
      	int Lin0 = 200;
      	int Lin1 = 250;
      	int Lin2 = 300;
      	int Lin3 = 350;
      	
      	String ret = a.InicString(); 	      					
		a.emNivel();
		a.r = a.Arvore[0];						
		for (int i = 1; i < 15; i++)		
		{	
			if (a.Arvore[i] == "@")
			{
			}
			else
			{
				if (a.compara(a.Arvore[i]+"",a.r+"") > 0)
				{
					a.verDir(a.Arvore[i]);
				}
				else
				{
					a.VerEsq(a.Arvore[i]);
				}
			}
		}			
		if (a.r != "@")
		{
			g.setColor(new Color(89,79,217));
			g.fillOval(160,Lin0,15,15);						
			g.drawString(a.r,160,Lin0+25);
			//Linha 1			
			if (a.le0 != "@")
			{
				g.fillOval( 80,Lin1,15,15);	
				g.drawLine(160+3, Lin0,80+3,Lin1);
				g.drawString(a.le0,80,Lin1+25);
			}
			
			if(a.ld0 != "@")
			{
				g.fillOval(240,Lin1,15,15);	
				g.drawLine(160+13, Lin0,240+12,Lin1);
				g.drawString(a.ld0,240,Lin1+25);	
			}
			
			//Linha 2
			if(a.le10 != "@")
			{
				g.fillOval( 40,Lin2,15,15);	
				g.drawLine(80+3, Lin1,40+3,Lin2);
				g.drawString(a.le10,40,Lin2+25);
			}
			if(a.le11 != "@")
			{
				g.fillOval(120,Lin2,15,15);	
				g.drawLine(80+13, Lin1,120+13,Lin2);
				g.drawString(a.le11,120,Lin2+25);
			}
			if(a.ld10 != "@")
			{
				g.fillOval(200,Lin2,15,15);
				g.drawLine(240+3, Lin1,200+3,Lin2);
				g.drawString(a.ld10,200,Lin2+25);
			}
			if(a.ld11 != "@")
			{			
				g.fillOval(280,Lin2,15,15);
				g.drawLine(240+13, Lin1,280+13,Lin2);
				g.drawString(a.ld11,280,Lin2+25);
			}
			
			//Linha 3		
			if(a.le20 != "@")
			{			
				g.fillOval(  20,Lin3,15,15);	
				g.drawLine( 40+3, Lin2,20+3,Lin3);
				g.drawString(a.le20,20,Lin3+25);
			}
			if(a.le21 != "@")
			{			
				g.fillOval(  60,Lin3,15,15);	
				g.drawLine( 40+13, Lin2,60+13,Lin3);
				g.drawString(a.le21,60,Lin3+25);
			}
			if(a.le22 != "@")
			{			
				g.fillOval( 100,Lin3,15,15);	
				g.drawLine( 120+3, Lin2,100+3,Lin3);
				g.drawString(a.le22,100,Lin3+25);
			}
			if(a.le23 != "@")
			{										
				g.fillOval( 140,Lin3,15,15);		
				g.drawLine( 120+13, Lin2,140+13,Lin3);
				g.drawString(a.le23,140,Lin3+25);
			}
			if(a.ld20 != "@")
			{								
				g.fillOval( 180,Lin3,15,15);	
				g.drawLine( 200+3, Lin2,180+3,Lin3);
				g.drawString(a.ld20,180,Lin3+25);
			}
			if(a.ld21 != "@")
			{								
				g.fillOval( 220,Lin3,15,15);	
				g.drawLine( 200+13, Lin2,220+13,Lin3);
				g.drawString(a.ld21,220,Lin3+25);
			}
			if(a.ld22 != "@")
			{									
				g.fillOval( 260,Lin3,15,15);								
				g.drawLine( 280+3, Lin2,260+3,Lin3);
				g.drawString(a.ld22,260,Lin3+25);
			}
			if(a.ld23 != "@")
			{								
				g.fillOval( 300,Lin3,15,15);	
				g.drawLine( 280+13, Lin2,300+13,Lin3);
				g.drawString(a.ld23,300,Lin3+25);
			}
		}
   	}	
	//Inicia Aplicativo	
	public static void main (String agrs[])		
	{	
		//NENE N�O ESQUECE DE ALTERAR AQUI E NO TITULO
		JOptionPane.showMessageDialog(null, 				
 		"Trabalho de Estrutura de Dados\n" +
		"Nomes\n" +
		"Jos� Maria de C. Gomes-41.696-7\n" +
		"Rafael V. da Silva-47.528-9\n" +
		"Elton dos Santos Araujo-47.693-5\n" +
		"Almir R. da Silva-48.159-9");
		Tela application = new Tela();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
}