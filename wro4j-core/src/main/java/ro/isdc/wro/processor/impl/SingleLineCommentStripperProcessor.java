/*
 * Copyright (c) 2008 ISDC! Romania. All rights reserved.
 */
package ro.isdc.wro.processor.impl;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import ro.isdc.wro.processor.ResourcePostProcessor;
import ro.isdc.wro.processor.ResourcePreProcessor;
import ro.isdc.wro.resource.Resource;
import ro.isdc.wro.util.WroUtil;

/**
 * SingleLineCommentStripperProcessor can be both: preProcessor & postProcessor.
 * Remove single line comments from processed resource. This processor exist only for the sake of proof of concept.
 *
 * @author Alex Objelean
 * @created Created on Nov 28, 2008
 */
public class SingleLineCommentStripperProcessor implements
    ResourcePreProcessor, ResourcePostProcessor {
  /**
   * Pattern containing a regex matching singleline comments & preceding empty
   * spaces & tabs.
   */
  public static Pattern PATTERN = Pattern.compile("[\\t ]*//.*?$",
      Pattern.MULTILINE);

  /**
   * {@inheritDoc}
   */
  public void process(final Reader source, final Writer destination)
      throws IOException {
    final String content = IOUtils.toString(source);
    String result = PATTERN.matcher(content).replaceAll("");
    result = WroUtil.EMTPY_LINE_PATTERN.matcher(result).replaceAll("");
    destination.write(result);
  }

  /**
   * {@inheritDoc}
   */
  public void process(final Resource resource, final Reader reader,
      final Writer writer) throws IOException {
    // resource Uri doesn't matter.
    process(reader, writer);
  }
}
