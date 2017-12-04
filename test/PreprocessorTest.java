import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;


public class PreprocessorTest<S,V> {
	
	Preprocessor<S, V> preprocessor;
	FileHandler filehandler;

	@Before
	public void setUp() throws Exception {
		filehandler = mock(FileHandler.class);
		preprocessor = new Preprocessor<S, V>(filehandler);
	}

	@Test
	public void stringWithSymbolShouldOpen() {
	when(preprocessor.filehandler.openFile("#")).thenReturn(true);
	verify(preprocessor.filehandler, times(1)).openFile("#");
	}
	
	@Test
	public void readNextSymbolShouldWork() {
	when(preprocessor.filehandler.hasMore())
	.thenReturn(true, true, false);
	when(preprocessor.filehandler.nextLine())
	.thenReturn("#ifdef","#");
	String shouldBe = "#ifdef  "; 
	assertThat(preprocessor.readNextSymbol(), is(shouldBe));

	verify(preprocessor.filehandler, times(3)).hasMore();
	verify(preprocessor.filehandler, times(2)).nextLine();
	}
	
}
