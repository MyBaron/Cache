package javax.cache.core.readthrough;


import javax.cache.integration.CompletionListener;

/**
 * @author lanruqi
 * @date 2019/8/22
 */
public class ReadThroughLoaderListener implements CompletionListener {

    public final static int COMPLETION = 1;
    public final static int LOADING = 0;
    public final static int EXCEPTION = -1;
    private Exception e;

    private int state=LOADING;


    @Override
    public void onCompletion() {
        this.state = COMPLETION;
    }

    @Override
    public void onException(Exception e) {
        this.state = EXCEPTION;
        this.e = e;
    }


    public Exception getE() {
        return e;
    }

    public int getState() {
        return state;
    }
}