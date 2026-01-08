package se.kry.hackdaysspringaidemo.ingest;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.DocumentReader;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class PdfIngestion {

  private final VectorStore vectorStore;

  private final Resource somePdf;

  public PdfIngestion(VectorStore vectorStore, @Value("classpath:Bullying and Harassment Policy.pdf") Resource somePdf) {
    this.vectorStore = vectorStore;
    this.somePdf = somePdf;
  }

  public void load() {
    var pdfReader = new PagePdfDocumentReader(somePdf);
    var tokenTextSplitter = new TokenTextSplitter(512, 350, 5, 10000, true);
    vectorStore.accept(tokenTextSplitter.apply(pdfReader.get()));
  }

}
