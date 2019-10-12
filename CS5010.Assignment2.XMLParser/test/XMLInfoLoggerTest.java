import org.junit.Assert;
import org.junit.Test;

/** Class for tests of XMLInfoLogger. */
public class XMLInfoLoggerTest {

  // test without sending an XML Structure
  @Test
  public void testWithoutSendingXMLStructure() throws InvalidXMLException {
    XMLInfoLogger obj = new XMLInfoLogger();
    Assert.assertEquals("", obj.output());
  }

  // test with a simple Tag
  @Test
  public void testWithSimpleTag() throws InvalidXMLException {
    XMLInfoLogger obj = new XMLInfoLogger();
    obj.input('<').input('a').input('>').input('A').input('<').input('/').input('a').input('>');
    Assert.assertEquals("Started:a\n" + "Characters:A\n" + "Ended:a\n", obj.output());
  }

  // test a nested tag with spaces \n and \t
  @Test
  public void testWithSpaceLineReturns() throws InvalidXMLException {
    XMLInfoLogger obj = new XMLInfoLogger();
    obj.input('<')
        .input('a')
        .input('>')
        .input(' ')
        .input('\t')
        .input('\n')
        .input('<')
        .input('/')
        .input('a')
        .input('>');
    Assert.assertEquals("Started:a\n" + "Characters: \t\n" + "\n" + "Ended:a\n", obj.output());
  }

  // test a yet to be completed XML for Output
  @Test
  public void testForYetToBeCompletedXML() throws InvalidXMLException {
    XMLInfoLogger obj = new XMLInfoLogger();
    obj.input('<').input('h').input('>').input(' ').input('<');
    Assert.assertEquals("Started:h\n", obj.output());
  }

  // test giving an exception for an unformed XML
  @Test(expected = InvalidXMLException.class)
  public void testInvalidXML() throws InvalidXMLException {
    XMLInfoLogger obj = new XMLInfoLogger();
    obj.input('<').input('0').input('>').input('T').input('<').input('/');
  }

  // test with 3rd level of tag nesting
  @Test
  public void test3LevelNesting() throws InvalidXMLException {
    XMLInfoLogger obj = new XMLInfoLogger();
    obj.input('<')
        .input('A')
        .input('>')
        .input('a')
        .input('<')
        .input('B')
        .input('>')
        .input('b')
        .input('<')
        .input('C')
        .input('>')
        .input('c')
        .input('<')
        .input('/')
        .input('C')
        .input('>')
        .input('<')
        .input('/')
        .input('B')
        .input('>')
        .input('<')
        .input('/')
        .input('A')
        .input('>');
    Assert.assertEquals(
        "Started:A\n"
            + "Characters:a\n"
            + "Started:B\n"
            + "Characters:b\n"
            + "Started:C\n"
            + "Characters:c\n"
            + "Ended:C\n"
            + "Ended:B\n"
            + "Ended:A\n",
        obj.output());
  }

  // test a nested completed tag
  @Test
  public void testNestedXMLWithNextLine() throws InvalidXMLException {
    XMLInfoLogger obj = new XMLInfoLogger();
    obj.input('<')
        .input('h')
        .input('t')
        .input('m')
        .input('l')
        .input('>')
        .input('N')
        .input('<')
        .input('a')
        .input('>')
        .input('<')
        .input('b')
        .input('>')
        .input('M')
        .input('<')
        .input('/')
        .input('b')
        .input('>')
        .input('<')
        .input('/')
        .input('a')
        .input('>')
        .input('B')
        .input('<')
        .input('/')
        .input('h')
        .input('t')
        .input('m')
        .input('l')
        .input('>');
    Assert.assertEquals(
        "Started:html\n"
            + "Characters:N\n"
            + "Started:a\n"
            + "Started:b\n"
            + "Characters:M\n"
            + "Ended:b\n"
            + "Ended:a\n"
            + "Characters:B\n"
            + "Ended:html\n",
        obj.output());
  }
}
