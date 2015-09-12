package livrariaweb.mb;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;

import livrariaweb.bean.Livro;
import livrariaweb.dao.LivroDao;
import livrariaweb.dao.LivroDaoImpl;

/*
• private LivroDao livroDao = new LivroDaoImpl(): obtem uma instancia de LivroDaoImpl
para acesso aos métodos de manipulação de dados.
• getLivro / setLivro: gets e sets referente ao objeto Livro.
• private boolean exibirForm = false: exibe ou oculta o formulário para cadastro/alteração
de dados dos livros.
• getLivros: obtem toda a lista de livros.
• adicionarLivro() / alterarLivro() / salvarLivro() / excluirLivro : respectivamente, adiciona,
altera dados, salva informações e exclui um livro da base de dados.
• cancelarCadastroLivro(): cancela o cadastro/alteração de um livro.
• limparLivro(): cria uma nova instancia do bean Livro.
• exibirForm() / ocultarForm(): exibe/oculta a visualização do formulário de cadastro e
alteração de dados.
• mostrarMensagem(): mostra as mensagens da aplicação.
 */

public class LivroMB {

	private LivroDao livroDao = new LivroDaoImpl();
	private Livro livro;
	private boolean exibirForm = false;
	
	public Livro getLivro() {
		return livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	public ListDataModel<Livro> getLivros() {
		return new ListDataModel<Livro>(livroDao.listarLivros());
	}
	
	public String adicionarLivro() {
		this.limparLivro();
		this.exibirForm();
		return null;
	}
	
	public String alterarLivro() {
		this.exibirForm();
		return null;
	}
	
	public String salvarLivro() throws Exception {		
		if (livro.getIdLivro() == null) {
			livroDao.insereLivro(livro);
		} 
		else {
			livroDao.alteraLivro(livro);
		}
		
		this.mostrarMensagem(livro.getTitulo() + " foi salvo!");
		this.ocultarForm();
		return null;
	}
	
	public String excluirLivro() throws Exception {
		livroDao.excluiLivro(livro);
		this.mostrarMensagem(livro.getTitulo() + " foi excluido!");
		return null;
	}
	
	public String cancelarCadastroLivro() {
		this.ocultarForm();
		return null;
	}
	
	private void limparLivro() {
		livro = new Livro();
	}
	
	private void exibirForm() {
		exibirForm = true;
	}
	
	private void ocultarForm() {
		exibirForm = false;
	}
	
	private void mostrarMensagem(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(mensagem));
	}
	
	public boolean isExibirForm() {
		return exibirForm;
	}
}
