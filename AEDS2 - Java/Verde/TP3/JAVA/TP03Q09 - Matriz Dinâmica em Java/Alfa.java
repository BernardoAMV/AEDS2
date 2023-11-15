public class Alfa{

	public static void main(String[]Args){
	
		int num = MyIO.readInt();
		Matriz m1;
		Matriz m2;

		for(int i = 0;i<num;i++){
		
			int linhas = MyIO.readInt();
			int colunas = MyIO.readInt();
			m1 = new Matriz(linhas,colunas);
			int[] elementos = new int[linhas*colunas];
			for(int j = 0;j<linhas*colunas;j++)
				elementos[j] = MyIO.readInt();
			
			m1.inserir(elementos);
			
			linhas = MyIO.readInt();
			colunas = MyIO.readInt();
			m2 = new Matriz(linhas,colunas);
			elementos = new int[linhas*colunas];
			
			for(int j = 0;j<linhas*colunas;j++)
				elementos[j] = MyIO.readInt();
			
			m2.inserir(elementos);

			m1.mostrarDiagonalPrincipal();

			m1.mostrarDiagonalSecundaria();
			Matriz m3 = m1.somaMatriz(m2);

			m3.mostrar();
			m3 = m1.multiplicaMatriz(m2);

			m3.mostrar();
		
		}
	
	
	}

}



class Celula {
   public int elemento;
   public Celula inf, sup, esq, dir;

   public Celula(){
      this(0, null, null, null, null);
   }

   public Celula(int elemento){
      this(elemento, null, null, null, null);
   }

   public Celula(int elemento, Celula inf, Celula sup, Celula esq, Celula dir){
      this.elemento = elemento;
      this.inf = inf;
      this.sup = sup;
      this.esq = esq;
      this.dir = dir;
   }
}



class Matriz {
   private Celula inicio;
   private int linha, coluna;

   public Matriz (){
      this.linha = this.coluna = 3;

      //alocar a matriz com this.linha linhas e this.coluna colunas
   }

   public Matriz (int linha, int coluna){
   
      this.linha = linha;
      this.coluna = coluna;
      //MyIO.println(linha + "X" + coluna);

         this.coluna = coluna;
        this.linha = linha;

        Celula tmp = new Celula();
        inicio = tmp;

        for(int i = 1 ;i<coluna;i++){
            tmp.dir = new Celula();
            tmp.dir.esq = tmp;
            tmp = tmp.dir;
        }
        tmp = inicio;
        for(int i = 1;i<linha;i++){
             
            tmp.inf = new Celula();
            tmp.inf.sup = tmp;
            tmp = tmp.inf;
           	Celula aux = tmp;
            for(int k = 1;k<coluna;k++){

                aux.dir = new Celula();
                aux.dir.esq = aux;
                aux.dir.sup = aux.sup.dir;
                aux.dir.sup.inf = aux.dir;
                aux = aux.dir;
            }
        }
        //mostrar();
   }
   public void inserir(int[] elementos){
   
		Celula tmp = inicio;
		int pos = 0;
		for(int i = 0;i<this.linha;i++){
		
			Celula tmp2 = tmp;
		
			for(int j = 0;j<this.coluna;j++){
			
				tmp2.elemento = elementos[pos];
				pos++;
				tmp2 = tmp2.dir;
			}
		
			tmp = tmp.inf;
		}
   		
   		//mostrar();
   }
   

   public Matriz somaMatriz (Matriz m) {
      Matriz resp = null;

      if(this.linha == m.linha && this.coluna == m.coluna){
         resp = new Matriz(this.linha,this.coluna);
         Celula tmp = this.inicio;
         Celula tmp2 = m.inicio;
         Celula tmp3 = resp.inicio;
         
         for(int i = 0;i<linha;i++){
				 
				 
			Celula aux = tmp;
         	Celula aux2 = tmp2;
         	Celula aux3 = tmp3;        	
				         
         	for(int j = 0;j<coluna;j++){

         		aux3.elemento = aux2.elemento + aux.elemento;
         		
         		aux = aux.dir;
         		aux2 = aux2.dir;
         		aux3 = aux3.dir;
         	}
         	
         	tmp = tmp.inf;
         	tmp2 = tmp2.inf;
         	tmp3 = tmp3.inf;
         
         
        }
      }

      return resp;
   }

   public Matriz multiplicaMatriz (Matriz m) {
      Matriz resp = null;

      if(this.coluna == m.linha){
      
      	  resp = new Matriz(this.linha,m.coluna);
	      Celula tmp = this.inicio;
	      Celula tmp2 = m.inicio;
          Celula tmp3 = resp.inicio;
          
          
          
      
      		for(int i = 0 ; i < resp.linha ; i++ ){

	  				
	  				Celula aux3 = tmp3;      		
	  				Celula aux2 = tmp2;

      			for(int j = 0 ; j < resp.coluna ; j++){

      				Celula ajudante = aux2;	
      		     	Celula aux = tmp;
      			
	  				for(int k = 0;k<m.linha;k++){

	  					
	  					
	  					aux3.elemento += aux.elemento * ajudante.elemento;
	  					aux = aux.dir;
	  					ajudante = ajudante.inf;
	  					
	  					
	  				}
	  				aux2 = tmp2.dir;
	  				aux3 = aux3.dir;
		  		}
		  		tmp = tmp.inf;
		  		tmp3 = tmp3.inf;
      		}
      }

      return resp;
   }

   public boolean isQuadrada(){
      return (this.linha == this.coluna);
   }

   public void mostrarDiagonalPrincipal (){
      if(isQuadrada() == true){
			
			Celula tmp;
      		for( tmp = inicio;tmp.dir!=null;tmp = tmp.dir.inf){
      			MyIO.print(tmp.elemento+" ");
      		}
      		MyIO.print(tmp.elemento + " ");
  	        MyIO.print("\n");

      }
   }

   public void mostrarDiagonalSecundaria (){
      if(isQuadrada() == true){
      		Celula tmp;
      		for(tmp = inicio;tmp.dir!=null;tmp = tmp.dir);
      		
      		for(;tmp.esq!=null;tmp = tmp.esq.inf)
      			MyIO.print(tmp.elemento+" ");
	
			MyIO.print(tmp.elemento + " ");
	        MyIO.print("\n");
	
      }
      
   }
   
   public void mostrar(){
   
   		Celula tmp = inicio;

   		for(int i = 0;i<linha;i++){
   		
   		
			Celula ajudante = tmp;
			for(int j = 0;j<coluna;j++){

				
				MyIO.print(ajudante.elemento + " ");
				ajudante = ajudante.dir;
			
			}
			MyIO.print("\n");
			tmp = tmp.inf;

		}   
   }
}
