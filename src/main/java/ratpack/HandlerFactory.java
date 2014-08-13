package ratpack;

import ratpack.guice.BindingsSpecAction;
import ratpack.handling.ChainAction;
import ratpack.handling.Context;
import ratpack.handling.Handler;
import ratpack.launch.LaunchConfig;

import static ratpack.guice.Guice.handler;

public class HandlerFactory implements ratpack.launch.HandlerFactory {
    @Override
    public Handler create(LaunchConfig launchConfig) throws Exception {
        return handler(launchConfig, new BindingsSpecAction() {
            @Override
            protected void execute() throws Exception {
                // noop
            }
        }, new Routes());
    }

    static class Routes extends ChainAction {

        @Override
        protected void execute() throws Exception {
            handler("foo", new Handler() {
                @Override
                public void handle(Context context) throws Exception {
                    context.getResponse().send("Yay! It's working!");
                }
            });
            assets("public", "index.html");
        }
    }
}
