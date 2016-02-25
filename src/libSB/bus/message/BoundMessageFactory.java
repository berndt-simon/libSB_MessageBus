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
package libSB.bus.message;

import libSB.bus.MessageBus;
import libSB.bus.topic.Topic;

/**
 *
 * @author Simon Berndt
 */
public class BoundMessageFactory<T> {

    private final Topic requestTopic;
    private final MessageBus boundBus;
    private final MessageFactory messageFactory;
    private final Class<T> type;

    public BoundMessageFactory(MessageBus boundBus, Topic topic, MessageFactory messageFactory, Class<T> type) {
        this.requestTopic = topic;
        this.boundBus = boundBus;
        this.messageFactory = messageFactory;
        this.type = type;
    }
    
    public void fireMessage(T contend) {
        Message<T> message = messageFactory.messageForContend(contend);
        boundBus.raise(requestTopic, message, type);
    }

}