### Run the app
_PRE: minikube is installed and working_
_PRE: istio is installed and it's working_
_PRE: label the target namespace to allow sidecar injection using ```kubectl label namespace default istio-injection=enabled```_

Run:
```
minikube start
eval $(minikube docker-env)
```
Then use the files below to manage the lifecycle:
```
buildAndPublish.sh
buildAndRun.sh
deployToK8s.sh
teardown.sh
```

Deploy the app
```kubectl apply -f k8s-first-app.yaml```

### Misc

#### Install istio
```
kubectl apply -f install/kubernetes/helm/helm-service-account.yaml
helm repo add istio.io https://storage.googleapis.com/istio-release/releases/1.2.2/charts/
helm init --service-account tiller
helm install install/kubernetes/helm/istio-init --name istio-init --namespace istio-system

helm install install/kubernetes/helm/istio --name istio --namespace istio-system \
    --values install/kubernetes/helm/istio/values-istio-demo.yaml
```

#### Port forward kiali
`kubectl port-forward svc/kiali 20001:20001 -n istio-system`

#### Hit the app through the ingress gateway using the node port
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
Install GralVM with the module _native-image_ to build the native version

### Start postgresql
```
docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 \
    --name postgres-quarkus-hibernate \
    -e POSTGRES_USER=hibernate -e POSTGRES_PASSWORD=hibernate -e POSTGRES_DB=hibernate_db \
    -p 5432:5432 \
    postgres:10.5
```