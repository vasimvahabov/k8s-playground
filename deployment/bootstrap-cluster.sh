set +ex

# minikube delete -p multinode

minikube start \
    -p multinode \
    --cpus=4 \
    --memory=4g \
    --driver=docker \
    --nodes=3

kubectl apply -f ./_cluster/
TOKEN=$(kubectl create token developer -n development)
kubectl config set-credentials developer --token=$TOKEN
# kubectl config set-context development --cluster=multinode --user=developer
