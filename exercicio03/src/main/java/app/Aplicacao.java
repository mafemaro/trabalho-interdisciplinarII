package app;

import static spark.Spark.*;

import service.ProdutoService;

public class Aplicacao {

    private static ProdutoService produtoService = new ProdutoService();

    public static void main(String[] args) {

        port(6784);

        staticFiles.location("/public");

    
        get("/", (req, res) -> {
            res.redirect("/index.html");
            return null;
        });

        // listar produtos
        get("/produto/list/1", (req, res) -> produtoService.get(req, res));

        // inserir produto
        post("/produto/insert", (req, res) -> produtoService.insert(req, res));

        // deletar produto
        get("/produto/delete/:id", (req, res) -> produtoService.delete(req, res));
    }
}