package testes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class JasperTesteLocal {
	
	public void gerar( String layout ) throws JRException , SQLException, ClassNotFoundException {
		//gerando o jasper design
		JasperDesign desenho = JRXmlLoader.load( layout );
		
		//compila o relatório
		JasperReport relatorio = JasperCompileManager.compileReport( desenho );
		
		//implementação da interface JRDataSource para DataSource ResultSet
		 List<Map<String, ?>> result = new ArrayList<>();
		 
		 Map<String, String> m = new HashMap<>();
         m.put("id", "1");
         m.put("nome", "jao");
         m.put("matricula", "m122");
         m.put("nascimento", "05-09-1993");
         result.add(m);
		
		//executa o relatório
		Map parametros = new HashMap();
		parametros.put("nota", new Double(10));
		
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(result);
		
		//JasperPrint impressao = JasperFillManager.fillReport( relatorio , parametros, jrRS );
		
		JasperPrint impressao = JasperFillManager.fillReport( relatorio , parametros, jrDataSource );
		
		//exibe o resultado
		JasperViewer viewer = new JasperViewer( impressao , true );
		viewer.show();
	}

	public static void main(String[] args) {
		try {
			//System.out.println("teste");
			new JasperTesteLocal().gerar( "D:/teste_jp.jrxml" );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
