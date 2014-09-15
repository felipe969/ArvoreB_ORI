/**
 * Autores: Cassio Greco, Felipe, Lucas, Lucas
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
            
            public boolean setDir(Node novoDir) {
                //Nao pode adicionar um numero menor ˆ direita
                if (valor > novoDir.getValor()) {
                    filhoDir = novoDir;
                    return true;
                }
                return false;
            }

            public boolean setEsq(Node novoEsq) {
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
        
        public Node getNode(Chave id){
        	for 
        }
        
    }
    	/**
    	 * Metodo que chama o metodo pesquisa_remova para a pesquisa de uma chave
    	 */
    	public boolean pesquisa(Chave chave,Node raiz){
    		return pesquisa_remova(chave,raiz,false);
    	}
    
    	/**
    	 * MŽtodo para pesqusiar se determinada chave est‡ dentro da arvore e/ou remove-la
    	 */
    	private boolean pesquisa_remova(Chave chave,Node raiz,boolean remova){
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
    					achou = true;
    					chave.no_id = no.id;
    					/**
    					 * Se a pesquisa for para remover uma chave
    					 */
    					if(remova){
    						if (no.length>no.min_chaves){
    							no.chaves[i] = null;
    							for(var k=i;k<no.length-1;k++)
    								no.chaves[k]=no.chaves[k+1];
    						}else{ //Se Ž necessario realizar uma fusao ou emprestimo
    							
    						}	
    					}
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
    	
    	/**
    	 * Metodo que chama pesquisa_remova para remover um no
    	 */
    	public boolean remover(Chave chave,Node raiz){
    		return pesquisa_remova(chave,raiz,true);
    		}
    			
    	}
    	
        public static void main(String[] args) {
            // TODO code application logic here
        }
    }
