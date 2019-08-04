## Run the app
###Build
_Requires GralVM with the native-image module and GRAALVM_HOME to be set_
```
./mvnw package -Pnative
docker build -f src/main/docker/Dockerfile.native -t k8s-first-app:0.0.20 .
```
###Run
```
docker run -i --rm -p 8080:8080 k8s-first-app 0.0.5
kubectl apply -f k8s-first-app.yaml
```

## Misc
### Install istio
Download istio from https://github.com/istio/istio/releases, unpack and cd to the folder
```
kubectl apply -f install/kubernetes/helm/helm-service-account.yaml
helm repo add istio.io https://storage.googleapis.com/istio-release/releases/1.2.2/charts/
helm init --service-account tiller
helm install install/kubernetes/helm/istio-init --name istio-init --namespace istio-system

helm install install/kubernetes/helm/istio --name istio --namespace istio-system \
    --values install/kubernetes/helm/istio/values-istio-demo.yaml
```

### Enable sidecar injection on namespace
```kubectl label namespace-name istio-injection=enabled```

### Port forward kiali
`kubectl port-forward svc/kiali 20001:20001 -n istio-system`

### Hit the app through the ingress gateway using the node port
```
export INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')
export SECURE_INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="https")].nodePort}')
export INGRESS_HOST=$(minikube ip)

curl http://$INGRESS_HOST:$INGRESS_PORT/hello
```

### Create a quarkus app
```
mvn io.quarkus:quarkus-maven-plugin:0.19.1:create \
    -DprojectGroupId=com.tyro.sfarsaci.experiments \
    -DprojectArtifactId=k8s-first-app \
    -DclassName="com.tyro.sfarsaci.experiments.GreetingResource" \
    -Dextensions="kotlin,resteasy-jsonb" \
    -Dpath="/hello"
```

### Start postgresql
```
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 \
    --name postgres-quarkus-hibernate \
    -e POSTGRES_USER=hibernate -e POSTGRES_PASSWORD=hibernate -e POSTGRES_DB=hibernate_db \
    -p 5432:5432 \
    postgres:10.5
```