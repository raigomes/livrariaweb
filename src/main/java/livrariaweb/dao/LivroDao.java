package livrariaweb.dao;

import java.util.List;
import livrariaweb.bean.Livro;

public interface LivroDao {
	public boolean insereLivro(Livro livro) throws Exception;
	public boolean alteraLivro(Livro livro) throws Exception;
	public boolean excluiLivro(Livro livro) throws Exception;
	public Livro consultarLivro(Livro livro) throws Exception;
	public List<Livro> listarLivros();
}