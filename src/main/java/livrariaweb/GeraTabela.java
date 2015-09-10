package livrariaweb;

import livrariaweb.bean.Livro;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class GeraTabela {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		AnnotationConfiguration conf = new AnnotationConfiguration()
				.configure("livrariaweb/util/hibernate.cfg.xml");
		conf.addAnnotatedClass(Livro.class);
		SchemaExport sE = new SchemaExport(conf);
		sE.create(true, true);
		System.out.println(" A Tabela " + Livro.class.getName() + " foi criada.");
	}
}