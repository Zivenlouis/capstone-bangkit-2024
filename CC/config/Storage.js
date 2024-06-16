import { Storage } from "@google-cloud/storage";
import 'dotenv/config';

let credentials;

try {
  if (!process.env.GOOGLE_CLOUD_KEY) {
    throw new Error("GOOGLE_CLOUD_KEY environment variable is not defined");
  }
  
  credentials = JSON.parse(process.env.GOOGLE_CLOUD_KEY);
} catch (error) {
  console.error("Error parsing GOOGLE_CLOUD_KEY:", error.message);
  process.exit(1); // Exit the process with an error code
}

const storage = new Storage({ credentials });
const bucket = storage.bucket("storage_auxilium");

export { storage, bucket };
