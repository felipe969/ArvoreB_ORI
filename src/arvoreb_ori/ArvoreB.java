/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoreb_ori;


/**
 * Autores: Cassio, Felipe, Lucas, Lucas
 * RAs: 379697,
 *
 * Classe ArvoreB contem dentro dela as classes Chave e Node
 *
 */

/**
 * falta acertar umas l—gicas em rela‹o aos n—s e chaves. mudei alguns construtores e atributos de Node
 */

public class ArvoreB {
		//variaveis public
		public Node raiz;
		//variaveis private
		private int ordem;
	
		public ArvoreB(int ordem_arvore) {
			ordem = ordem_arvore;
			raiz = null;
		}

		public ArvoreB(Node novaRaiz,int ordem_arvore) {
			raiz = novaRaiz;
			ordem = ordem_arvore;
		}

		public ArvoreB(int novaRaiz,int ordem_arvore) {
			raiz = new Node(novaRaiz);
			int ordem_arvore;
		}
		
		public Node getRaiz(){
			return raiz;
		}
		//falta trabalhar

        public class Chave {
        	
        	//variaveis private
            private int valor;
            //variaveis public
            /**
             * Mudeu para node, pois as chaves apontam para nodes e n‹o para chaves espec’ficas
             * Criei o atributo no_id (n— id) para saber em qual n— a chave est‡
             */
            public Node filhoDir, filhoEsq;
            int no_id;
            
          //construtores
            public Chave() {
                filhoDir = null;
                filhoEsq = null;
            }

            public Chave(Node novoDir, Node novoEsq, Chave novoValor) {
                filhoDir = novoDir;
                filhoEsq = novoEsq;
                valor = novoValor;
            }

            public Chave(int novoValor) {
                valor = novoValor;
                filhoDir = null;
                filhoEsq = null;
            }

            public int getValor() {
                return valor;
            }

            public void setValor(int novoValor) {
                valor = novoValor;
            }
  
            public Node getDir() {
                return filhoDir;
            }
            
            public Node getEsq() {
                return filhoEsq;
            }
            
            /**
             * Falta mudar os novoDir e novoEsq para Node, pois eles apontam para nodes e nao chaves
             */
            
            public boolean setDir(Chave novoDir) {
                //Nao pode adicionar um numero menor ˆ direita
                if (valor > novoDir.getValor()) {
                    filhoDir = novoDir;
                    return true;
                }
                return false;
            }

            public boolean setEsq(Chave novoEsq) {
            	//Nao pode adicionar um numero maior ˆ direita
                if (valor < novoEsq.getValor()) {
                    filhoEsq = novoEsq;
                    return true;
                }
                return false;
            }

        }

    public class Node {

        private int ordem;
        private Chave []chaves;
        private boolean folha;
        private int chavesUso;
        //Para sabermos se Ž preciso fundir e/ou emprestar de outros n—s
        private int min_chaves = (ordem-1)/2;
        //Para sabermos quais chaves est‹o em quais nodes
        public int id;

        //construtores
        public Node(int novaOrdem) {
            ordem = novaOrdem;
            chavesUso = 0;
        }
        
        public Node(Node raiz, int ordem_arvore){
        	raiz.ordem = ordem_arvore;
        	raiz.id = 0;
        }

        //construtor de fusao
        public Node(Node c1, Node c2, int antigoPai) {
            int i = 0;
            int j = 0;

            //copia primeiro no antigo na primeira parte do novo no
            while (c1.chaves[i] != null) {

                if (chavesUso == ordem - 1) {
                    System.out.print("Chave lotada!");
                } else {
                    chaves[j] = c1.chaves[i];
                    i++;
                    j++;
                }
            }

            //coloca o antigo pai
            i = 0;

            if (chavesUso == ordem - 1) {
                System.out.print("Chave lotada!");
            } else {
                chavesUso++;
                chaves[j] = new chave(antigoPai);

            }
            //copia o segundo numero
            while (c2.chaves[j] != null) {

                if (chavesUso == ordem - 1) 
                    System.out.print("Chave lotada!");
                 else {
                    chaves[j] = c2.chaves[i];
                    i++;
                    j++;
                    chavesUso++;
                }


            }
            c1 = null;
            c2 = null;
        }
        
    }
    
    	/**
    	 * MŽtodo para pesqusiar se determinada chave est‡ dentro da arvore
    	 */
    	public boolean pesquisa(Chave chave){
    		boolean achou = false;
    		Node no = raiz;
    		do{
    			/**
    			 * Se o no pesquisado Ž vazio, a chave n‹o foi achada.
    			 * Retorna false
    			 */
    			if(no == null)
    				return achou;
    			for(int i=0;i<no.chaves.length;i++){
    				/**
    				 * Chave encontrada. Retorna true e a chave recebe o id do no
    				 */
    				if(chave.getValor()==no.chaves[i].getValor()){
    					achou == true;
    					chave.no_id = no.id;
    				}
    				/**
    				 * Se a chave pesquisada for menor do que alguma chave do n—
    				 * n‹o h‡ porque iterar mais.
    				 * Desce-se para o n— filho esquerdo da chave
    				 * que Ž maior do que o n— pesquisado
    				 */
    				if(chave.getValor()<no.chaves[i].getValor()){
    					no = no.chaves[i].filhoEsq();
    					break;
    				}
    				/**
    				 * Sen‹o, ele verificou todas as chaves e Ž maior do que
    				 * a maior chave que est‡ no n— e ent‹o desce-se para o filho
    				 * ˆ direita do œltimo n— da chave
    				 */
    				if(chave.getValor()>no.chaves[length-1].getValor())
    					no = no.chaves[i].getDir();			
    			}
    		}while(achou!=true);
    		/**
    		 * Retorna true se a chave foi encontrada
    		 */
    		return achou;
    	}
    	
    	public boolean remover(Chave chave){
    		boolean removido = false;
    		/**
    		 * Se a chave n‹o for encontrada, n‹o pode ser removida.
    		 * Return false
    		 */
    		if(!pesquisa(chave))
    			return removido;
    		else{
    			/**
    			 * Se a chave for encontrada, pega-se o n— com o id de onde a chave est‡,
    			 * e verifica se o tamanho de chaves[] Ž maior do que min_chaves (n‹o Ž necess‡rio emprŽstimo)
    			 * a chave Ž removida do n—.
    			 * 
    			 * Se o tamanho de chaves[] == min_chaves, um emprŽstimo Ž necess‡rio, tenho que verificar o tamanho dos n—s irmaos
    			 * E precisa fazer a fus‹o tambŽm
    			 */
    			if(Node no)
    		}
    			
    	}
    	
        public static void main(String[] args) {
            // TODO code application logic here
        }
    }
