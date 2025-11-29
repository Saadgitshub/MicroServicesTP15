package com.example.tp15.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {

        // Si c’est une RuntimeException qu’on a levée nous-mêmes (ex: compte non
        // trouvé)
        if (ex instanceof RuntimeException) {
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(graphql.ErrorType.DataFetchingException)
                    .build();
        }

        // Pour toutes les autres exceptions (erreurs inattendues)
        return GraphqlErrorBuilder.newError(env)
                .message("Erreur serveur interne : " + ex.getMessage())
                .errorType(graphql.ErrorType.ExecutionAborted)
                .build();
    }
}