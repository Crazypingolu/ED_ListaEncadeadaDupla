package model.estrutura;

import java.lang.Exception;
import model.estrutura.No;

public class ListaEncadeadaDupla<T> {
		private No<T> inicio = null;
		private No<T> fim = null;
		private int total;
		
		public void setTotal(int novoTotal){
			this.total = novoTotal;
		}
		
		public int getTotal(){
			return total;
		}
		
		public void append (T elemento){
			No<T> buffer = new No<>(elemento);
			if (this.inicio == null){
				this.fim = buffer;
				this.inicio = buffer;
			} else {
				No<T> ex_ultimo = this.fim;
				ex_ultimo.setProximo(buffer);
				buffer.setAnterior(ex_ultimo);
				this.fim = buffer;
			}
			setTotal( ++total );
		}
		
		public No<T> get (int index) throws IllegalArgumentException{
			int i = 0;
			if (this.inicio == null) 
				throw new IllegalArgumentException("Nao existe item na lista.");
			No<T> buffer = this.inicio;
			for(i = 0; i < index; i++){
				if(buffer.getProximo() == null)
					break;
				buffer = buffer.getProximo();
			}
			if(i < index)
				throw new IllegalArgumentException("O indice informado não existe");
			return buffer;
		}
		
		public int index(T elemento) throws IllegalArgumentException{
			if (this.inicio == null)
				throw new IllegalArgumentException("Não existe item na lista");
			int index = 0;
			if (this.inicio.getValor() == elemento)
				return index;
			No<T> buffer = this.inicio;
			do {
				if( buffer.getValor() == elemento){
					return index;
				}
				buffer = buffer.getProximo();
				index++;
			} while(buffer != null);
			throw new IllegalArgumentException("Item não encontrado");
		}
		
		public void insert(int index, T elemento) throws IllegalArgumentException{
			if (index == 0){
				No<T> novo = new No<> (elemento);
				if( this. inicio == null ){
					this.inicio = novo;
					this.fim = novo;
				} else {
					No<T> ex_inicio = this.inicio;
					novo.setProximo (ex_inicio);
					this.inicio = novo;
					novo.setProximo(ex_inicio);
					ex_inicio.setAnterior(novo);
				}
			} else {
				this.insert(this.get(--index), elemento);
			}
			setTotal( ++total );
		}
		
		public void insert(No<T> item, T elemento) throws IllegalArgumentException {
			No<T> buffer_novo = new No<T>(elemento);
			No<T> buffer_proximo = item.getProximo();
			item.setProximo(buffer_novo);
			buffer_novo.setProximo(buffer_proximo);
			buffer_proximo.setAnterior(buffer_novo);
			buffer_novo.setAnterior(item);
			setTotal( ++total );
		}
		
		public No<T> last() throws IllegalArgumentException{
			if (this.inicio == null )
				throw new IllegalArgumentException ("Não existe item na lista");
			No<T> buffer = this.inicio;
			while(buffer.getProximo() != null)
				buffer = buffer.getProximo();
			return buffer;
		}
		
		public void remove(int index){
			if(index == 0){
				this.inicio.setValor(null);
				this.inicio.setAnterior(null);
				if(this.inicio.getProximo() == null){
					this.inicio = null;
				} else {
					No<T> buffer = this.inicio.getProximo();
					this.inicio.setProximo(null);
					this.inicio = buffer;
				}
				return;
			}
			No<T> buffer_anterior = this.get(--index);
			No<T> item = buffer_anterior.getProximo();
			No<T> buffer_proximo = item.getProximo();
			buffer_anterior.setProximo(buffer_proximo);
			item.setProximo(null);
			item.setValor(null);
			
			setTotal( --total );
		}
		
		public void prepend(T elemento){
			No<T> buffer = new No<>(elemento);
			if (this.fim == null){
				this.fim = buffer;
				this.inicio = buffer;
			} else {
				No<T> ex_primeiro = this.inicio;
				this.inicio = buffer;
				buffer.setProximo(ex_primeiro);
				ex_primeiro.setAnterior(buffer);
			}
		}
		
		@Override
		public String toString(){
			if (this.inicio == null){
				return "{}";
			}
			StringBuilder builder = new StringBuilder("[");
			No<T> buffer = this.inicio;
			builder.append(buffer.getValor());
			while(buffer.getProximo() != null){
				builder.append(",");
				buffer = buffer.getProximo();
				builder.append(buffer.getValor());
			}
			builder.append("]");
			return builder.toString();
		}
}