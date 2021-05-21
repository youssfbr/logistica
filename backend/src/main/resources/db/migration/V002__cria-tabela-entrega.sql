CREATE TABLE tb_entrega (
	
	id BIGSERIAL PRIMARY KEY,
	cliente_id BIGINT NOT NULL,
	taxa DECIMAL(10,2) NOT NULL,
	status VARCHAR(20) NOT NULL,
	data_pedido timestamp default NULL NOT NULL,
	data_finalizacao timestamp default NULL,
	telefone VARCHAR(20) NOT NULL,
	
	destinatario_nome VARCHAR(60) NOT NULL,
	destinatario_logradouro VARCHAR(255) NOT NULL,
	destinatario_numero VARCHAR(30) NOT NULL,
	destinatario_complemento VARCHAR(60) NOT NULL,
	destinatario_bairro VARCHAR(30) NOT NULL
);

ALTER TABLE tb_entrega ADD CONSTRAINT fk_entrega_cliente
FOREIGN KEY (cliente_id) REFERENCES tb_cliente (id);
