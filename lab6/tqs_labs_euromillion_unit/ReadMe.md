# 6.1

O projeto passou no Quality Gate, com zero bugs ("A"), zero vulnerabilidade ("A"], mas uma security Hotspot que tem um estada de Security Review de "E". A Coverage foi de 69.0% em 8 unidades de teste e as Duplications foi de 0%.

## Bugs

Não encontrou nenhum bug

## Vulnerabilidades

Não encontrou nenhuma vulnerabilidade

## Security hotspots

### Problem

Make sure that using this pseudorandom number generator is safe here.

```    static Random generator = new Random();```

### Problem description

Usar geradores de números pseudoaleatórios (PRNGs) é sensível à segurança.  

Por exemplo, isso no passado conduziu às seguintes vulnerabilidades:

-  [CVE-2013-6386](http://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2013-6386) 
-  [CVE-2006-3419](http://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2006-3419) 
-  [CVE-2008-4102](http://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2008-4102) 

Quando o software gera valores previsíveis, em um contexto que requer imprevisibilidade, é possível para um invasor adivinhar o próximo valor que será gerado e usar essa suposição para se fazer passar por outro utilizador ou acessar informações confidenciais.

Como a classe java.util.Random é basiada em um gerador de números pseudoaleatórios, essa classe e o método java.lang.Math.random() relacionado, não devem ser usados para aplicativos críticos de segurança ou mesmo para proteger dados confidenciais. Neste contexto, a classe java.security.SecureRandom, que se baseia em um gerador de números aleatórios criptograficamente forte, deve ser usada no seu lugar.

## Solution

Usar um gerador de números aleatórios criptograficamente forte, como "java.security.SecureRandom", em vez deste PRNG.

Usar os valores aleatórios gerados apenas uma vez.

Não expor o valor aleatório gerado. Se for preciso armazená-lo,  certificar que o banco de dados ou arquivo seja seguro.

## Major code smell

### Problem

Invoke method(s) only conditionally

### Description

Passar os argumentos de mensagem que requerem uma avaliação adicional em um método de verificação com.google.common.base.Preconditions do Guava pode resultar em uma penalidade de desempenho. Isso ocorre porque, independentemente de estes serem necessários ou não, cada argumento deve ser resolvido antes que o método seja efetivamente chamado.

Da mesma forma, passar strings concatenadas em um método de registo também pode acabar em uma penalidade de desempenho desnecessária porque a concatenação será realizada toda vez que o método for chamado, independentemente do nível de log, ser baixo o suficiente para mostrar a mensagem.

Em vez disso, deve-se estruturar o código para passar valores estáticos ou pré-computados em chamadas de verificação de Preconditions e de registo.

### Solution

```logger.log(Level.SEVERE, "Something went wrong: {0} ", message);  // String formatting only applied if needed
logger.fine("An exception occurred with message: {}", message);  // SLF4J, Log4j

logger.log(Level.SEVERE, () -> "Something went wrong: " + message); // since Java 8, we can use Supplier , which will be evaluated lazily

LOG.error("Unable to open file {0}", csvPath, e);

if (LOG.isDebugEnabled()) {
  LOG.debug("Unable to open file " + csvPath, e);  // this is compliant, because it will not evaluate if log level is above debug.
}

Preconditions.checkState(arg > 0, "Arg must be positive, but got %d", a);  // String formatting only applied if needed

if (!condition) {
  throw new IllegalStateException(formatMessage());  // formatMessage() only invoked conditionally
}

if (!condition) {
  throw new IllegalStateException("message: " + formatMessage());
}
```

### Problem

Refactor the code in order to not assign to this loop counter from within the loop body.

### Description

A condição que para o  "for loop" deve testar o contador do loop em relação a um valor invariante (ou seja, verdadeiro no início e no final de cada iteração do loop). Idealmente, isso significa que esta condição é definida como uma variável local antes do início do loop.

Condições de stop, que não são invariantes, são ligeiramente menos eficientes, além de serem difíceis de entender e manter. Provavelmente estas levam à introdução de erros no futuro.

Esta regra rastreia três tipos de condições de stop não-invariantes:

- Quando os contadores do loop são atualizados no corpo do "for loop";
- Quando a condição de stop depende de uma chamada ao método;
- Quando a condição de stop depende de uma propriedade do objeto, pois tais propriedades podem mudar durante a execução do loop.

### Solution

```for (int i = 0; i < 10; i++) {...}```

### Problem

Remove this unused method parameter "subset".

### Description

Parâmetros não utilizados são enganosos. Independentemente dos valores  passados, o comportamento será o mesmo.

### Solution

```
void doSomething(int a) {
  compute(a);
}
```



