# Osgi guide

## Open Service Getway initiative

_É uma especificação que produzir um sistema dinâmico de **componentes** Java, com o objetivo de reduzir a complexidade de um software através de uma arquitetura modular orientada a serviços._

## Bundles

_São modulos que possuem identificadores únicos (Manifesto) com cobeçalhos especificos (válidos) e devem ser armazenados em um arquivo JAR. Podemos dizer que o Bundle é a unidade básica de modularidade._

_Na identificação do bundle temos dois cabeçalhos específicos no manifesto que são utilizados para identificar um Bundles, são eles:_

* __*Bundle-SymbolicName*__
* __*Bundle-Version*__

## Versinamento Semântico

![This is a alt text.](https://www.matteus.dev/wp-content/uploads/versionamento-semantico-2.png "This is a sample image.")

### Intervalo de versão

Exemplo: version= **[** 1.2, 2.0 **)**

_Nesse caso estamos informando que as versões que devem ser utilzadas pelo meu Bundles está entre 1.2 e inevitavelmente antes da 2.0 (existe funcionalidades incompatíveis com as versões anteriores na versão 2.0)_

_Ou seja, se observarmos bem. Utilizamos o **colchete para incluir versões** e o **parenteses para excluir versões** incompatíveis_

### Ciclo de vida de um Bundle

_Um Bundle inevitavelmente possui 6 estados, sendo eles:_
  1. Installed
  1. Resolved
  1. Starting
  1. Active
  1. Stopping
  1. Uninstalled

![This is a alt text.](https://wiki.cdot.senecacollege.ca/w/imgs/Bundle-lifecycle.png "This is a sample image.")

_Podemos **gerenciar o ciclo de vida de um Bundle** usando linha de comando. Utilizando o Gogo Shell que é um recurso que permite acessar o container OSGI utilizanfo o Telnet._

_Uma vez presente no Gogo Shell podemos utilizar alguns comandos para gerenciar o bundle como:_

1. Comandos:

 1. install caminho/para/bundle/nomeBundle.jar
 1. uninstall bundleId
 1. resolve bundleId
 1. update bundleId
 1. refresh bundleId
 1. start bundleId
 1. stop bundleId

## Componente

_Componente é um objeto que possui uma funcuionalidade especifica. Exemplo: Serviço, Servelet, Portlet e Comandos._

_Qualquer classe dentro de um Bundle pode ser declarada como um componente._

_Component é um objeto gerenciado pelo OSGI e possui um ciclo de vida, assim como o bundle._

_O componente ficará dentro do Bundle e o Container OSGI cuidará dos dois._

_A vantagem de utilziar uma classe como um componente é que elas podem ser publicadas como serviços e estar disponíveis para outros componentes. Além disso, possui um ciclo de vida independente e reutilizado, podendo possuir propriedades e activação._

_O **@component** é usando para registrar um componente dentro do registro de serviços._

## Serciço

_O serviço é um componente registrado pelo Service Registry no container do OSGI._

_O bundle pode declarar as funcionalidades que ele fornece como serviço OSGI._

_O bundle pode pedir por específica funcionalidade nos serciços OSGI._

_O Service Registry funciona como uma destribuidora que recebe serviços exportados pelos Bundles e envia serviços para os Bundles que estão solicitando._

## Declarative Sercices - DS

_É um Framework de ingestão de dependência OSGI, permite declarar e consumir serviços via XML e anotações. Inicializa a classe, injeta as dependencias, ativa os componentes e registra os serviços._

_O **@reference** é utilizado para solicitar ao registro de serviço uma referencia de um componente específico._

> Markdown is a lightweight markup language with plain-text-formatting syntax, created in 2004 by John Gruber with Aaron Swartz.
>
>> Markdown is often used to format readme files, for writing messages in online discussion forums, and to create rich text using a plain text editor.

## Construindo um projeto OSGI

![This is a alt text.](https://wiki.idempiere.org/w-en/images/7/7c/Layering-osgi.png "This is a sample image.")

_Inicialmente criamos um diretório que irá conter nosso projeto._

_Em seguida, criamos o arquivo **settings.gradle**_

```
buildscript {
	dependencies {
		classpath group: "com.liferay", name: "com.liferay.gradle.plugins.workspace", version: "latest.release"
	}

	repositories {
		mavenLocal()

		maven {
			url "https://repository-cdn.liferay.com/nexus/content/groups/public"
		}
	}
}

apply plugin: "com.liferay.workspace"
```

_As informações presentes em settings.gradle são responsáveis por criar o com.liferay.workspace Gradle Plugin_

_Em seguida é criado o arquivo gradle.properties que seleciona uma versão específica do Portal_

**gradle.properties**
```
liferay.workspace.product=portal-7.3-ga7
```

## _Criando o primeiro modulo(Bundle)_

_Inicialmente criamos uma estrutura de pasta._

```
 mkdir -p basic-training-able-impl/src/main/java/com/liferay/basic/training/able/internal/activator
```

_Posteriormente criamos um arquivo **.jar**, no diretório recem criado **activate**, nomeado de **AbleBundleActivator** e adicionamos as seguintes informações:_

```
package com.liferay.basic.training.able.internal.activator;

public class AbleBundleActivator {

	public AbleBundleActivator() {
		System.out.println("Constructing AbleBundleActivator");
	}

}
```

_Porém ao tentarmos compilar nosso programa:_

```
./gradlew classes
```

_Iremos perceber que as features Gradle utilizadas nesse Build estão depreciadas, mas o comando foi executado com sucesso._

_Porém o diretório de compilação **build** não foi criado._

_Ainda em nosso diretório **activator** devemos criar um arquivo de configuração com os metadados basícos necessários para os modulos OSGI._

```
code basic-training-able-impl/bnd.bnd
```

```
Bundle-Name: Liferay Basic Training Able Implementation
Bundle-SymbolicName: com.liferay.basic.training.able.impl
Bundle-Version: 1.0.0
```

_Em seguida, iremos criar um arquivo build.gradle_

```
touch basic-training-able-impl/build.gradle
```

_Um arquivo build.gradle informa ao Liferay Workspace que esse diretório é um módulo Java._

_Finalmente, ao tentarnos compilar novamente nosso programa:_

```
./gradlew classes
```

_Iremos perceber que um novo diretório **(build)** se encontra em basic-training-able-impl._


