package livrariaweb.dao;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import livrariaweb.bean.Livro;
import livrariaweb.util.HibernateUtil;

/*
• session = HibernateUtil.getSessionFactory().openSession(): A partir da classe HibernateUtil e do objeto 
sessionFactory uma sessão com o banco de dados é aberta. Tal sessão é atribuída a variável session.

• session.persist: Insere um registro no banco de dados.
• session.update: Atualiza um registro no banco de dados.
• session.delete: Exclui um registro no banco de dados.
• session.get: Obtém um registro no banco de dados.
• session.createQuery: Permite definir uma SQL para ser executada no banco dados. Neste caso, 
traz uma lista de livros.

• transaction = session.beginTransaction(): Abre um processo de transação no banco de dados.
• transaction.commit(): Comita, persiste as informações enviadas para o banco de dados.
• transaction.rollback(): Desfaz tudo caso ocorra algum problema durante o processamento de alguma transação. 
 */

public class LivroDaoImpl implements LivroDao {

	private Session session = null;
	private Transaction  transaction = null;
	
	@Override
	public boolean insereLivro(Livro livro) throws Exception {		
		boolean retorno = false;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession(); 
			transaction = (Transaction) session.beginTransaction();  
			System.out.println("DAO - autor: " + livro.getAutor() );
			
			session.persist(livro);
			
			transaction.commit();
			retorno = true;
			
			System.out.println("Livro adicionado");
		}
		catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		
		
		return retorno;
	}

	@Override
	public boolean alteraLivro(Livro livro) throws Exception{
		boolean retorno = false;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = (Transaction) session.beginTransaction();

			session.update(livro);
			
			transaction.commit();
			retorno = true;
			
			System.out.println("Livro alterado");
		}
		catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		
		return retorno;
	}

	@Override
	public boolean excluiLivro(Livro livro) throws Exception{
		boolean retorno = false;
		
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = (Transaction) session.beginTransaction();
			
			session.delete(session.get(Livro.class, livro.getIdLivro()));
			
			transaction.commit();
			retorno = true;
			
			System.out.println("Livro removido");
		}
		catch(HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		
		return retorno;
	}

	@Override
	public Livro consultarLivro(Livro livro) {
		Livro l = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			l = (Livro) session.get(Livro.class, livro.getIdLivro());
		} 
		catch (HibernateException e) {
			e.printStackTrace();
		} 
		finally {
			session.close();
		}
		
		return l;
	}

	@Override
	public List<Livro> listarLivros() {
		List<Livro> list = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			list = session.createQuery("select l from Livro l").list();
		} 
		catch (HibernateException e) {
			e.printStackTrace();
		} 
		finally {
			session.close();
		}
		
		return list;
	}

}
