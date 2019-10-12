import org.junit.Assert;
import org.junit.Test;

/** Class for tests of the XMLValidator Class. */
public class XMLValidatorTest {

  // Test Empty Status
  @Test
  public void testEmptyStatus() throws InvalidXMLException {
    XMLValidator obj = new XMLValidator();
    Assert.assertEquals("Status:Empty", obj.output());
  }

  @Test(expected = InvalidXMLException.class)
  public void testEmptyTagButValidXML() throws InvalidXMLException {
    XMLValidator obj = new XMLValidator();
    obj.input('<').input('>').input('<').input('/').input('>');
  }

  // Test simple Tag
  @Test
  public void testSimpleTag() throws InvalidXMLException {
    XMLValidator obj = new XMLValidator();
    obj.input('<')
        .input('h')
        .input('t')
        .input('m')
        .input('l')
        .input('>')
        .input('<')
        .input('/')
        .input('h')
        .input('t')
        .input('m')
        .input('l')
        .input('>');
    Assert.assertEquals("Status:Valid", obj.output());
  }

  // Test simple Tag with characters
  @Test
  public void testSimpleTagWithCharacters() throws InvalidXMLException {
    XMLValidator obj = new XMLValidator();
    obj.input('<')
        .input('h')
        .input('t')
        .input('m')
        .input('l')
        .input('>')
        .input('B')
        .input('o')
        .input('s')
        .input('t')
        .input('o')
        .input('n')
        .input('<')
        .input('/')
        .input('h')
        .input('t')
        .input('m')
        .input('l')
        .input('>');
    Assert.assertEquals("Status:Valid", obj.output());
  }

  // Test simple Nested Tag with characters
  @Test
  public void testSimpleNestedTagWithCharacters() throws InvalidXMLException {
    XMLValidator obj = new XMLValidator();
    obj.input('<')
        .input('h')
        .input('t')
        .input('m')
        .input('l')
        .input('>')
        .input('B')
        .input('o')
        .input('s')
        .input('t')
        .input('o')
        .input('n')
        .input('<')
        .input('p')
        .input('>')
        .input('c')
        .input('o')
        .input('m')
        .input('m')
        .input('o')
        .input('n')
        .input('<')
        .input('/')
        .input('p')
        .input('>')
        .input('<')
        .input('/')
        .input('h')
        .input('t')
        .input('m')
        .input('l')
        .input('>');
    Assert.assertEquals("Status:Valid", obj.output());
  }

  // Test simple Peer Tag with characters
  @Test
  public void testSimplePeerTagWithCharacters() throws InvalidXMLException {
    XMLValidator obj = new XMLValidator();
    obj.input('<')
        .input('h')
        .input('>')
        .input('B')
        .input('<')
        .input('a')
        .input('>')
        .input('i')
        .input('s')
        .input('<')
        .input('/')
        .input('a')
        .input('>')
        .input('<')
        .input('b')
        .input('>')
        .input('g')
        .input('o')
        .input('o')
        .input('d')
        .input('<')
        .input('/')
        .input('b')
        .input('>')
        .input('<')
        .input('/')
        .input('h')
        .input('>');
    Assert.assertEquals("Status:Valid", obj.output());
  }

  // test with Invalid Tags
  @Test(expected = InvalidXMLException.class)
  public void testInvalidTags() throws InvalidXMLException {
    XMLValidator obj = new XMLValidator();
    obj.input('<').input('!');
  }

  // test with reserved < > tags in characters
  @Test(expected = InvalidXMLException.class)
  public void testReservedTags() throws InvalidXMLException {
    XMLValidator obj = new XMLValidator();
    obj.input('<')
        .input('a')
        .input('>')
        .input('T')
        .input('<')
        .input('<')
        .input('/')
        .input('a')
        .input('>');
  }

  // test tag with number as a starting character
  @Test(expected = InvalidXMLException.class)
  public void testTagStartWithNumber() throws InvalidXMLException {
    XMLValidator obj = new XMLValidator();
    obj.input('<').input('0').input('>').input('T').input('<').input('/').input('0').input('>');
  }

  // test tag with - as a starting character
  @Test(expected = InvalidXMLException.class)
  public void testTagStartWithInvalidChar() throws InvalidXMLException {
    XMLValidator obj = new XMLValidator();
    obj.input('<').input('-').input('>').input('T').input('<').input('/').input('-').input('>');
  }

  // test tag with an invalid character
  @Test(expected = InvalidXMLException.class)
  public void testTagWithInvalidChar() throws InvalidXMLException {
    XMLValidator obj = new XMLValidator();
    obj.input('<')
        .input('a')
        .input('#')
        .input('$')
        .input('>')
        .input('<')
        .input('/')
        .input('a')
        .input('#')
        .input('$')
        .input('>');
  }

  // test Input Status for a multi level nested tag
  @Test(expected = InvalidXMLException.class)
  public void testStatusForNestedXML() throws InvalidXMLException {
    XMLValidator obj = new XMLValidator();
    obj.input('<')
        .input('a')
        .input('>')
        .input('A')
        .input('<')
        .input('b')
        .input('>')
        .input('t')
        .input('<')
        .input('b')
        .input('>')
        .input('<')
        .input('c')
        .input('>')
        .input('t')
        .input('<')
        .input('/')
        .input('c')
        .input('>')
        .input('<')
        .input('/')
        .input('a')
        .input('>');
  }

  // test multiple root tags
  @Test(expected = InvalidXMLException.class)
  public void testMultipleRoots() throws InvalidXMLException {
    XMLValidator obj = new XMLValidator();
    obj.input('<')
        .input('a')
        .input('>')
        .input('<')
        .input('/')
        .input('a')
        .input('>')
        .input('<')
        .input('b')
        .input('>')
        .input('<')
        .input('/')
        .input('b')
        .input('>');
  }

  // test spaces, line return and tabs in Characters
  @Test
  public void testSpaces() throws InvalidXMLException {
    XMLValidator obj = new XMLValidator();
    obj.input('<')
        .input('a')
        .input('>')
        .input(' ')
        .input(' ')
        .input(' ')
        .input('\n')
        .input('\t')
        .input('<')
        .input('/')
        .input('a')
        .input('>');
    Assert.assertEquals("Status:Valid", obj.output());
  }

  // test special characters in characters
  @Test
  public void testSpecialCharacters() throws InvalidXMLException {
    XMLValidator obj = new XMLValidator();
    obj.input('<')
        .input('a')
        .input('>')
        .input('@')
        .input('#')
        .input('$')
        .input('%')
        .input('^')
        .input('<')
        .input('/')
        .input('a')
        .input('>');
    Assert.assertEquals("Status:Valid", obj.output());
  }

  // check status on various different types of non ending xmls
  @Test
  public void testStatusOfIncomplete() throws InvalidXMLException {
    XMLValidator obj1 = new XMLValidator();
    XMLValidator obj2 = new XMLValidator();
    XMLValidator obj3 = new XMLValidator();
    XMLValidator obj4 = new XMLValidator();
    XMLValidator obj5 = new XMLValidator();

    obj1.input('<').input('h').input('t');
    Assert.assertEquals("Status:Incomplete", obj1.output());

    obj2.input('<')
        .input('h')
        .input('>')
        .input('<')
        .input('a')
        .input('>')
        .input('T')
        .input('<')
        .input('/');
    Assert.assertEquals("Status:Incomplete", obj2.output());

    obj3.input('<')
        .input('a')
        .input('>')
        .input('<')
        .input('b')
        .input('>')
        .input('I')
        .input('<')
        .input('/')
        .input('b')
        .input('>');
    Assert.assertEquals("Status:Incomplete", obj3.output());

    obj4.input('<').input('h').input('>').input('<');
    Assert.assertEquals("Status:Incomplete", obj4.output());

    obj5.input('<')
        .input('h')
        .input('>')
        .input('<')
        .input('a')
        .input('>')
        .input('<')
        .input('/')
        .input('a')
        .input('>')
        .input('<')
        .input('b')
        .input('>')
        .input('<')
        .input('c')
        .input('>')
        .input('<')
        .input('/');
    Assert.assertEquals("Status:Incomplete", obj5.output());
  }
}
