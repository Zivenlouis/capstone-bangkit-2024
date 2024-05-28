import express from "express";
import db from "../config/Database.js";
import {
  getUsers,
  getUsersById,
  createUser,
  loginUser,
} from "../controllers/Users.js";
import { loginFilter } from "../filters/LoginFilter.js";
import {
  getSmartphones,
  getSmartphonesByBrand,
  getSmartphonesById,
} from "../controllers/Smartphones.js";
import {
  getCommunity,
  getCommunityById,
  createCommunity,
} from "../controllers/Community.js";
import {
  getCommunity_image,
  getCommunity_imageById,
  createCommunity_image,
} from "../controllers/Community_image.js";
import {
  getUserRatings,
  createUserRatings,
  getUserRatingsBySmartphoneId,
} from "../controllers/UserRatings.js";

const router = express.Router();

router.get("/users", loginFilter, getUsers);
router.get("/users/:id", loginFilter, getUsersById);
router.get("/smartphones", loginFilter, getSmartphones);
router.get("/smartphones/brand/:brand", loginFilter, getSmartphonesByBrand);
router.get("/smartphones/:id", loginFilter, getSmartphonesById);
router.get("/community", loginFilter, getCommunity);
router.get("/community/:id", loginFilter, getCommunityById);
router.get("/community_images", getCommunity_image);
router.get("/community_images/:id", getCommunity_imageById);
router.post("/users", createUser);
router.post("/login", loginUser);
router.post("/community/add", loginFilter, createCommunity);
router.post("/community_images/add", createCommunity_image);

export default router;
