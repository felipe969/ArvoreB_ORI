/**
 * Autores: 
 * Cassio Greco RA: 379697,
 * Felipe Fantoni RA: 407747, 
 * Lucas H. Pereira RA: 408220,
 * Lucas Lima Oliveira RA 407755
 *
 *
 * Classe ArvoreB contem dentro dela as classes Chave e Node
 *
 */

/**
 * falta acertar umas l�gicas em rela��o aos n�s e chaves. mudei alguns construtores e atributos de Node
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
             * Mudeu para node, pois as chaves apontam para nodes e n�o para chaves espec�ficas
             * Criei o atributo no_id (n� id) para saber em qual n� a chave est�
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
                //Nao pode adicionar um numero menor � direita
                if (valor > novoDir.getValor()) {
                    filhoDir = novoDir;
                    return true;
                }
                return false;
            }

            public boolean setEsq(Node novoEsq) {
            	//Nao pode adicionar um numero maior � direita
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
        //Para sabermos se � preciso fundir e/ou emprestar de outros n�s
        private int min_chaves = (ordem-1)/2;
        //Para sabermos quais chaves est�o em quais nodes
        public int id;
        public Node pai;

        //construtores
        public Node(int novaOrdem) {
            ordem = novaOrdem;
            chavesUso = 0;
        }

        public Node(Node pai, int novaOrdem) {
            ordem = novaOrdem;
        }
        
        /*
        public Node(Node raiz, int ordem_arvore){
        	raiz.ordem = ordem_arvore;
        	raiz.id = 0;
        }
        */

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
        
        /*
        public Node getNode(Chave id){
        	for 
        }
        */
        
        public void insereNo (int X)
    {
           int i=0;
           //checa lotado
           if(chavesUso==ordem-1){
               Node novoNo = new Node(pai,ordem);
              for(i=ordem/2+1;i<ordem-1;i++){
                  novoNo.insereNo(chaves[i].getValor());
                  chavesUso--;
                  
              }
               pai.insereNo(chaves[ordem/2].getValor());
               pai.chaves[pai.chavesUso].filhoDir= novoNo;
           }
           else{
            chavesUso++;
            chaves[chavesUso].setValor(X);
           }
       
       
    }
        
    }
    	/**
    	 * Metodo que chama o metodo pesquisa_remova para a pesquisa de uma chave
    	 */
    	public boolean pesquisa(Chave chave,Node raiz){
    		return pesquisa_remova(chave,raiz,false);
    	}
    
    	/**
    	 * M�todo para pesqusiar se determinada chave est� dentro da arvore e/ou remove-la
    	 */
    	private boolean pesquisa_remova(Chave chave,Node raiz,boolean remova){
    		boolean achou = false;
                int l;
    		Node no = raiz;
    		do{
    			/**
    			 * Se o no pesquisado � vazio, a chave n�o foi achada.
    			 * Retorna false
    			 */
    			if(no == null)
    				return achou;
    			for(int i=0;i<no.chavesUso;i++){
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
    						if (no.chavesUso > no.min_chaves){
    							no.chaves[i] = null;
    							for(int k=i; k < no.chavesUso-1; k++)
    								no.chaves[k]=no.chaves[k+1];
                                                                no.chavesUso--;
                                                        
    						}else{ 
                                                       for(l=0;l<no.pai.chavesUso;l=l+2){
                                                           if(no.pai.chaves[l].getEsq()==no || no.pai.chaves[l].getDir()==no){
                                                               break;
                                                           }
                                                       }
                                                //achado o no pai, ver se possivel rotacionar
                                                    if( no.pai.chaves[l].getEsq().chavesUso>(ordem-1)/2) 
                                                        //faz a rotacao com a esq
                                                        
                                                        
                                                        
                                                    else
                                                        if( no.pai.chaves[l].getDir().chavesUso<(ordem-1)/2)    
                                                        //faz a rotadao com a dir
                                                            
                                                            
                                                        
                                                        else
                                                           //faz a fusao 
                                                            
                                                    
    							
    						}	
    					}
    				}
    				/**
    				 * Se a chave pesquisada for menor do que alguma chave do n�
    				 * n�o h� porque iterar mais.
    				 * Desce-se para o n� filho esquerdo da chave
    				 * que � maior do que o n� pesquisado
    				 */
                                
    				if(chave.getValor() < no.chaves[i].getValor()){
    					no = no.chaves[i].getEsq();
    					break;
    				}
    				/**
    				 * Sen�o, ele verificou todas as chaves e � maior do que
    				 * a maior chave que est� no n� e ent�o desce-se para o filho
    				 * � direita do �ltimo n� da chave
    				 */
    				if(chave.getValor() > no.chaves[no.chavesUso-1].getValor())
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
        
        // Insere na arovre 
        public void insere(int X) {
            Node busca = raiz;
            int i = 0;
            //só insere em folha
            while (!busca.folha) {
                if (busca.chaves[i].getValor() > X) {
                    busca = busca.chaves[i].getEsq();
               
                i = 0;
                 }
                else{
                            if(busca.chaves[i].getValor()<X && busca.chaves[i+1].getValor()>X)
                                busca=busca.chaves[i].getDir();
                                i=0;
                        }
                i = i + 2;

            }
            
            busca.insereNo(X);
         
        }
        
        
        
    			
    	
    	
        public static void main(String[] args) {
            // TODO code application logic here
        }
    }
