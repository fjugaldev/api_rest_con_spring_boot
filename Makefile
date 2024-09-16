PROJECT_NAME=mandalorian
SERVICE_NAME=mandalorian-api
GOOGLE_CLOUD_REGION=us-west1

gc-login:
	gcloud auth login

gc-activate-project:
	gcloud config set project $(PROJECT_NAME)

gc-build:
	gcloud builds submit --tag gcr.io/$(PROJECT_NAME)/$(SERVICE_NAME)

gc-deploy:
	gcloud run deploy --image gcr.io/$(PROJECT_NAME)/$(SERVICE_NAME) --platform managed --region=$(GOOGLE_CLOUD_REGION)