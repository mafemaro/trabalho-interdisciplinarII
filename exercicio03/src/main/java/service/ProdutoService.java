package service;

import dao.ProdutoDAO;
import model.Produto;
import spark.Request;
import spark.Response;

import java.util.ArrayList;

public class ProdutoService {

    private ProdutoDAO produtoDAO = new ProdutoDAO();

    public Object insert(Request req, Response res) {
        try {
            String nome = req.queryParams("nome");
            String precoStr = req.queryParams("preco");
            String descricao = req.queryParams("descricao");

            if(nome == null || nome.isEmpty() ||
               precoStr == null || precoStr.isEmpty() ||
               descricao == null || descricao.isEmpty()) {
                return "<div style='font-family:sans-serif; text-align:center; margin-top:50px;'>" +
                       "<h1 style='color:#e74c3c;'>Erro: Todos os campos são obrigatórios!</h1>" +
                       "<a href='/produto/form' style='color:#3498db; text-decoration:none;'>Voltar</a></div>";
            }
            precoStr = precoStr.replaceAll("[^0-9.]", "");

            float preco;
            try {
                preco = Float.parseFloat(precoStr);
            } catch (NumberFormatException e) {
                return "<div style='font-family:sans-serif; text-align:center; margin-top:50px;'>" +
                       "<h1 style='color:#e74c3c;'>Erro: Preço inválido!</h1>" +
                       "<a href='/produto/form' style='color:#3498db; text-decoration:none;'>Voltar</a></div>";
            }

            Produto produto = new Produto(0, nome, preco, descricao);

            boolean sucesso = produtoDAO.insert(produto);

            if(!sucesso){
                return "<div style='font-family:sans-serif; text-align:center; margin-top:50px;'>" +
                       "<h1 style='color:#e74c3c;'>Erro ao salvar no banco!</h1>" +
                       "<a href='/produto/form' style='color:#3498db; text-decoration:none;'>Voltar</a></div>";
            }

            res.redirect("/produto/list/1");
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return "<div style='font-family:sans-serif; text-align:center; margin-top:50px;'>" +
                   "<h1 style='color:#e74c3c;'>Erro interno no servidor!</h1>" +
                   "<a href='/produto/form' style='color:#3498db; text-decoration:none;'>Voltar</a></div>";
        }
    }

    public Object get(Request req, Response res) {
        ArrayList<Produto> lista = produtoDAO.get();

        StringBuilder html = new StringBuilder();
        html.append("<html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        html.append("<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap' rel='stylesheet'>");
        html.append("<style>");
        html.append("body { font-family: 'Poppins', sans-serif; background-color: #f4f7f6; margin: 0; padding: 40px; color: #333; }");
        html.append("h1 { text-align: center; color: #2c3e50; margin-bottom: 40px; font-weight: 600; }");
        html.append(".container { display: grid; grid-template-columns: repeat(auto-fill, minmax(280px, 1fr)); gap: 20px; max-width: 1200px; margin: 0 auto; }");
        html.append(".card { background: white; padding: 25px; border-radius: 15px; box-shadow: 0 10px 20px rgba(0,0,0,0.05); transition: transform 0.3s ease; border-top: 5px solid #ff66b3; }");
        html.append(".card:hover { transform: translateY(-5px); }");
        html.append(".card b { font-size: 1.2rem; color: #ff66b3; display: block; margin-bottom: 10px; }");
        html.append(".price { font-size: 1.1rem; font-weight: 600; color: #27ae60; margin-bottom: 10px; display: block; }");
        html.append(".desc { font-size: 0.9rem; color: #7f8c8d; line-height: 1.5; }");
        html.append(".btn-back { display: block; width: 150px; margin: 40px auto; text-align: center; padding: 12px; background: #2c3e50; color: white; text-decoration: none; border-radius: 8px; transition: background 0.3s; }");
        html.append(".btn-back:hover { background: #34495e; }");
        html.append("</style></head><body>");

        html.append("<h1>✨ Lista de Produtos</h1>");
        html.append("<div class='container'>");

        for (Produto p : lista) {
            html.append("<div class='card'>");
            html.append("<b>").append(p.getNome()).append("</b>");
            html.append("<span class='price'>R$ ").append(String.format("%.2f", p.getPreco())).append("</span>");
            html.append("<div class='desc'>").append(p.getDescricao()).append("</div>");
            html.append("</div>");
        }

        html.append("</div>");
        html.append("<a href='/' class='btn-back'>← Voltar</a>");
        html.append("</body></html>");

        return html.toString();
    }

    public Object delete(Request req, Response res) {
        return "delete não implementado";
    }

    public Object update(Request req, Response res) {
        return "update não implementado";
    }

    public String form() {
        return "<html><head><meta charset='UTF-8'><meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                + "<link href='https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;600&display=swap' rel='stylesheet'>"
                + "<style>"
                + "body { font-family: 'Poppins', sans-serif; background: linear-gradient(135deg, #fdfbfb 0%, #ebedee 100%); display: flex; justify-content: center; align-items: center; min-height: 100vh; margin: 0; }"
                + ".form-card { background: white; padding: 40px; border-radius: 20px; box-shadow: 0 15px 35px rgba(0,0,0,0.1); width: 90%; max-width: 400px; }"
                + "h2 { color: #ff66b3; text-align: center; margin-bottom: 30px; }"
                + ".input-group { margin-bottom: 20px; }"
                + "label { display: block; font-size: 0.85rem; color: #666; margin-bottom: 5px; margin-left: 5px; }"
                + "input { width: 100%; padding: 12px; border: 2px solid #eee; border-radius: 10px; outline: none; transition: border-color 0.3s; box-sizing: border-box; font-family: inherit; }"
                + "input:focus { border-color: #ff66b3; }"
                + "input[type='submit'] { background: #ff66b3; color: white; border: none; font-weight: 600; cursor: pointer; margin-top: 10px; transition: background 0.3s; font-size: 1rem; }"
                + "input[type='submit']:hover { background: #ff4da6; }"
                + "</style></head><body>"
                + "<div class='form-card'>"
                + "<h2>Novo Produto</h2>"
                + "<form action='/produto/insert' method='post'>"
                + "<div class='input-group'><label>Nome do Produto</label><input name='nome' placeholder='Ex: Notebook' required></div>"
                + "<div class='input-group'><label>Preço</label><input name='preco' placeholder='0.00' required></div>"
                + "<div class='input-group'><label>Descrição</label><input name='descricao' placeholder='Detalhes...' required></div>"
                + "<input type='submit' value='Cadastrar Produto'>"
                + "</form>"
                + "</div>"
                + "</body></html>";
    }
}