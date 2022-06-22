# Setup local access to cluster

1. Install OCI CLI

2. Create kube.config via OCI CLI

3. Deploy the kubernetes dashboard in the new cluster `kubectl apply -f https://raw.githubusercontent.com/kubernetes/dashboard/v2.5.0/aio/deploy/recommended.yaml`

4. Set up admin service account

5. Get auth token for admin service account

`kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep oke-admin | awk '{print $1}')`

6. `kubectl proxy`
