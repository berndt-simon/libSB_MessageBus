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
import libSB.bus.topic.matcher.TopicPatternMatcher;

/**
 *
 * @author Simon Berndt
 */
public final class Topics {

    public static final Topic ROOT_TOPIC = new RootTopic();

    public static final TopicMatcher ANY_TOPIC = (Topic topic) -> true;

    private Topics() {
    }

    public static TopicMatcher pattern(String topicPattern) {
        return new TopicPatternMatcher(topicPattern);
    }

    public static Topic createRequestSibling(Topic topic) {
        String leafSubTopic = topic.getLeafSubTopic();
        return topic.createSibling(Topic.REQUEST_PREFIX + Character.toUpperCase(leafSubTopic.charAt(0)) + leafSubTopic.substring(1));
    }

    public static Topic createResonseSibling(Topic topic) {
        String leafSubTopic = topic.getLeafSubTopic();
        return topic.createSibling(Topic.RESPONSE_PREFIX + Character.toUpperCase(leafSubTopic.charAt(0)) + leafSubTopic.substring(1));
    }

    public static Topic createCallbackSibling(Topic topic) {
        String leafSubTopic = topic.getLeafSubTopic();
        return topic.createSibling(Topic.CALLBACK_PREFIX + Character.toUpperCase(leafSubTopic.charAt(0)) + leafSubTopic.substring(1));
    }

}
