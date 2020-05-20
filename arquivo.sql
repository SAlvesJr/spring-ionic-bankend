-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: curso_spring
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.37-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `prod_categ`
--

DROP TABLE IF EXISTS `prod_categ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prod_categ` (
  `produto_id` bigint(20) NOT NULL,
  `categori_id` bigint(20) NOT NULL,
  KEY `FKcsg7j1bhrghp09e96inkq1yxd` (`categori_id`),
  KEY `FKbluur7st4yhpthvlb5h9ew9jd` (`produto_id`),
  CONSTRAINT `FKbluur7st4yhpthvlb5h9ew9jd` FOREIGN KEY (`produto_id`) REFERENCES `tb_produto` (`id`),
  CONSTRAINT `FKcsg7j1bhrghp09e96inkq1yxd` FOREIGN KEY (`categori_id`) REFERENCES `tb_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prod_categ`
--

LOCK TABLES `prod_categ` WRITE;
/*!40000 ALTER TABLE `prod_categ` DISABLE KEYS */;
INSERT INTO `prod_categ` VALUES (1,1),(1,4),(2,1),(2,2),(2,4),(3,1),(3,4),(4,2),(5,3),(6,3),(7,4),(8,5),(9,6),(10,6),(11,7);
/*!40000 ALTER TABLE `prod_categ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_category`
--

DROP TABLE IF EXISTS `tb_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_category`
--

LOCK TABLES `tb_category` WRITE;
/*!40000 ALTER TABLE `tb_category` DISABLE KEYS */;
INSERT INTO `tb_category` VALUES (1,'Informática'),(2,'Escritório'),(3,'Cama mesa e banho'),(4,'Eletrônicos'),(5,'Jardinagem'),(6,'Decoração'),(7,'Perfumaria');
/*!40000 ALTER TABLE `tb_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_item_pedido`
--

DROP TABLE IF EXISTS `tb_item_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_item_pedido` (
  `desconto` double DEFAULT NULL,
  `preco` double DEFAULT NULL,
  `quantidade` int(11) DEFAULT NULL,
  `produto_id` bigint(20) NOT NULL,
  `pedido_id` bigint(20) NOT NULL,
  PRIMARY KEY (`pedido_id`,`produto_id`),
  KEY `FKgfmv77km3wt2evaaq2vkiv2oj` (`produto_id`),
  CONSTRAINT `FK2943tm7di708aa311cvgrqrw1` FOREIGN KEY (`pedido_id`) REFERENCES `tg_pedido` (`id`),
  CONSTRAINT `FKgfmv77km3wt2evaaq2vkiv2oj` FOREIGN KEY (`produto_id`) REFERENCES `tb_produto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_item_pedido`
--

LOCK TABLES `tb_item_pedido` WRITE;
/*!40000 ALTER TABLE `tb_item_pedido` DISABLE KEYS */;
INSERT INTO `tb_item_pedido` VALUES (0,2000,1,1,1),(0,80,2,3,1),(100,800,1,2,2);
/*!40000 ALTER TABLE `tb_item_pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pagamento_boleto`
--

DROP TABLE IF EXISTS `tb_pagamento_boleto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_pagamento_boleto` (
  `data_pagamento` datetime DEFAULT NULL,
  `data_vencimento` datetime DEFAULT NULL,
  `pedido_id` bigint(20) NOT NULL,
  PRIMARY KEY (`pedido_id`),
  CONSTRAINT `FKjnb62ts48elfl65o3ghx5pnl6` FOREIGN KEY (`pedido_id`) REFERENCES `tg_pagamento` (`pedido_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pagamento_boleto`
--

LOCK TABLES `tb_pagamento_boleto` WRITE;
/*!40000 ALTER TABLE `tb_pagamento_boleto` DISABLE KEYS */;
INSERT INTO `tb_pagamento_boleto` VALUES (NULL,'2017-10-20 03:00:00',2);
/*!40000 ALTER TABLE `tb_pagamento_boleto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pagamento_cartao`
--

DROP TABLE IF EXISTS `tb_pagamento_cartao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_pagamento_cartao` (
  `numeros_parcelas` int(11) DEFAULT NULL,
  `pedido_id` bigint(20) NOT NULL,
  PRIMARY KEY (`pedido_id`),
  CONSTRAINT `FK1qiu80pqlrc1vni1uaakfbblg` FOREIGN KEY (`pedido_id`) REFERENCES `tg_pagamento` (`pedido_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pagamento_cartao`
--

LOCK TABLES `tb_pagamento_cartao` WRITE;
/*!40000 ALTER TABLE `tb_pagamento_cartao` DISABLE KEYS */;
INSERT INTO `tb_pagamento_cartao` VALUES (6,1);
/*!40000 ALTER TABLE `tb_pagamento_cartao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_produto`
--

DROP TABLE IF EXISTS `tb_produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_produto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `preco` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_produto`
--

LOCK TABLES `tb_produto` WRITE;
/*!40000 ALTER TABLE `tb_produto` DISABLE KEYS */;
INSERT INTO `tb_produto` VALUES (1,'Computador',2000),(2,'Impressaora',800),(3,'Mouse',80),(4,'Mesa de escritório',300),(5,'Toalha',50),(6,'Colcha',200),(7,'TV true color',1200),(8,'Roçadeira',800),(9,'Abajour',100),(10,'Pendente',180),(11,'Shampoo',90);
/*!40000 ALTER TABLE `tb_produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefone`
--

DROP TABLE IF EXISTS `telefone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefone` (
  `cliente_id` bigint(20) NOT NULL,
  `telefones` varchar(255) DEFAULT NULL,
  KEY `FK8sddha0f9vdvp9j46j6xwt8wp` (`cliente_id`),
  CONSTRAINT `FK8sddha0f9vdvp9j46j6xwt8wp` FOREIGN KEY (`cliente_id`) REFERENCES `tg_cliente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefone`
--

LOCK TABLES `telefone` WRITE;
/*!40000 ALTER TABLE `telefone` DISABLE KEYS */;
INSERT INTO `telefone` VALUES (1,'27363323'),(1,'93838393');
/*!40000 ALTER TABLE `telefone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tg_cidade`
--

DROP TABLE IF EXISTS `tg_cidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tg_cidade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `estado_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK33a1gin69h4kb12m4s3ss1tl4` (`estado_id`),
  CONSTRAINT `FK33a1gin69h4kb12m4s3ss1tl4` FOREIGN KEY (`estado_id`) REFERENCES `tg_estado` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tg_cidade`
--

LOCK TABLES `tg_cidade` WRITE;
/*!40000 ALTER TABLE `tg_cidade` DISABLE KEYS */;
INSERT INTO `tg_cidade` VALUES (1,'Uberlândia',1),(2,'São Paulo',2),(3,'Campinas',2);
/*!40000 ALTER TABLE `tg_cidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tg_cliente`
--

DROP TABLE IF EXISTS `tg_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tg_cliente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf_ou_cnpj` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `tipo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ldgn54w1mlbe2v8xk9pmbwvy1` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tg_cliente`
--

LOCK TABLES `tg_cliente` WRITE;
/*!40000 ALTER TABLE `tg_cliente` DISABLE KEYS */;
INSERT INTO `tg_cliente` VALUES (1,'11613377096','maria@email.com','Maria',1);
/*!40000 ALTER TABLE `tg_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tg_endereco`
--

DROP TABLE IF EXISTS `tg_endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tg_endereco` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `cidade_id` bigint(20) DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3d3ie0stp4w9qsaxu0dfb43xp` (`cidade_id`),
  KEY `FK1dpxvmpk83ehbandw9togrxc4` (`cliente_id`),
  CONSTRAINT `FK1dpxvmpk83ehbandw9togrxc4` FOREIGN KEY (`cliente_id`) REFERENCES `tg_cliente` (`id`),
  CONSTRAINT `FK3d3ie0stp4w9qsaxu0dfb43xp` FOREIGN KEY (`cidade_id`) REFERENCES `tg_cidade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tg_endereco`
--

LOCK TABLES `tg_endereco` WRITE;
/*!40000 ALTER TABLE `tg_endereco` DISABLE KEYS */;
INSERT INTO `tg_endereco` VALUES (1,'Jardim','38220834','Apto 203','Rua Flores','300',1,1),(2,'Centro','38777012','Sala 800','Avenida Matos','105',2,1);
/*!40000 ALTER TABLE `tg_endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tg_estado`
--

DROP TABLE IF EXISTS `tg_estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tg_estado` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tg_estado`
--

LOCK TABLES `tg_estado` WRITE;
/*!40000 ALTER TABLE `tg_estado` DISABLE KEYS */;
INSERT INTO `tg_estado` VALUES (1,'Minas Gerais'),(2,'São Paulo');
/*!40000 ALTER TABLE `tg_estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tg_pagamento`
--

DROP TABLE IF EXISTS `tg_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tg_pagamento` (
  `pedido_id` bigint(20) NOT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`pedido_id`),
  CONSTRAINT `FK2i0ujmdwr3otpcpcx606585vy` FOREIGN KEY (`pedido_id`) REFERENCES `tg_pedido` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tg_pagamento`
--

LOCK TABLES `tg_pagamento` WRITE;
/*!40000 ALTER TABLE `tg_pagamento` DISABLE KEYS */;
INSERT INTO `tg_pagamento` VALUES (1,2),(2,1);
/*!40000 ALTER TABLE `tg_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tg_pedido`
--

DROP TABLE IF EXISTS `tg_pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tg_pedido` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `instante` datetime DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `endereco_entrega_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK23r4axo6rwoioj9qixpedh03y` (`cliente_id`),
  KEY `FKokxkbo4be9eh89m18mqyjmja8` (`endereco_entrega_id`),
  CONSTRAINT `FK23r4axo6rwoioj9qixpedh03y` FOREIGN KEY (`cliente_id`) REFERENCES `tg_cliente` (`id`),
  CONSTRAINT `FKokxkbo4be9eh89m18mqyjmja8` FOREIGN KEY (`endereco_entrega_id`) REFERENCES `tg_endereco` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tg_pedido`
--

LOCK TABLES `tg_pedido` WRITE;
/*!40000 ALTER TABLE `tg_pedido` DISABLE KEYS */;
INSERT INTO `tg_pedido` VALUES (1,'2024-06-30 13:32:00',1,1),(2,'2017-10-10 22:35:00',1,2);
/*!40000 ALTER TABLE `tg_pedido` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-20 12:51:30
