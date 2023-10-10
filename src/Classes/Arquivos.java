package Classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Arquivos {
    private String nome;
    private double valorTotal;
    private int quantidade;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Arquivos(String nome, double valorTotal, int quantidade) {
        this.nome = nome;
        this.valorTotal = valorTotal;
        this.quantidade = quantidade;
    }

    public void leituraGravacao() {
        // Caminho do arquivo para ler
        String filePath = "C:\\temp\\dados.CSV";

        // Inicializar a lista fora do loop
        ArrayList<Arquivos> minhaLista = new ArrayList<>();

        // Leitura do arquivo
        try (BufferedReader buffer = new BufferedReader(new FileReader(filePath))) {
            String line = buffer.readLine();
            while (line != null) {
                // Identificar o separador
                String[] dados = line.split(",");

                if (dados.length == 3) {
                    // Converter os valores do array para os tipos apropriados
                    nome = dados[0];
                    valorTotal = Double.parseDouble(dados[1]);
                    quantidade = Integer.parseInt(dados[2]);
                    valorTotal = valorTotal * quantidade;

                    // Criar um objeto Arquivos com os dados
                    Arquivos arquivo = new Arquivos(nome, valorTotal, quantidade);

                    // Adicionar o objeto à lista
                    minhaLista.add(arquivo);
                }

                line = buffer.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("CRIANDO ARQUIVO SUMMARY\n");

        // criar o arquivo novo
        String arquivoNovo = "C:\\temp\\summary.CSV";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoNovo))) {
            for (Arquivos arquivos : minhaLista) {
                // Salvar no novo arquivo
                writer.write(arquivos.getNome() + ',' + arquivos.getValorTotal());
                writer.newLine();

            }
            System.out.println("Summary Finalizado");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
