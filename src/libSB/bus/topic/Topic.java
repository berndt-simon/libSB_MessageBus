/* 
 * The MIT License
 *
 * Copyright 2016 Simon Berndt.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package libSB.bus.topic;

import libSB.bus.topic.matcher.TopicMatcher;

/**
 *
 * @author Simon Berndt
 */
public interface Topic extends TopicMatcher {
    
    final char LEVEL_SEPERATOR = '/';
    
    final String REQUEST_PREFIX = "request";
    final String RESPONSE_PREFIX = "response";
    final String CALLBACK_PREFIX = "callback";
    
    Topic getParent();
    Topic createChild(String childName);
    Topic createSibling(String siblingName);
    
    Topic createAsChildOf(Topic parentTopic);
    
    String getLeafSubTopic();
    
    @Override
    default boolean matches(Topic topic) {
        if (topic == null) {
            return false;
        }
        return topic.toString().equals(toString());
    }
        
    @Override
    abstract String toString();
    
}
