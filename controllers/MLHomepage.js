import url_homepage from "../config/MachineLearning.js";
import Smartphones from "../models/SmartphonesModel.js";
import UserClicks from "../models/UserClicksModel.js";
import UserRatings from "../models/UserRatingsModel.js";
import axios from "axios";

export const getTopSmartphones = async (req, res) => {
  try {
    const userId = req.params.id;
    let user_click_raw = await UserClicks.findAll({
      where: {
        user_id: userId,
      },
      order: [["createdAt", "DESC"]],
      limit: 20,
    });
    let user_rating_raw = await UserRatings.findAll({
      where: {
        user_id: userId,
      },
      order: [["createdAt", "DESC"]],
      limit: 5,
    });
    let phone_count = await Smartphones.count();
    phone_count = phone_count.count;
    const user_click = {};

    user_click_raw.forEach((click) => {
      const smartphoneId = click.smartphone_id;
      if (!user_click[smartphoneId]) {
        user_click[smartphoneId] = 0;
      }
      user_click[smartphoneId]++;
    });

    const user_rating = {};

    user_rating_raw.forEach((click) => {
      const smartphoneId = click.smartphone_id;
      if (!user_rating[smartphoneId]) {
        user_rating[smartphoneId] = 0;
      }
      user_rating[smartphoneId]++;
    });

    const data = {
      phone_count: phone_count,
      user_click: user_click,
      user_rating: user_rating,
    };
    const response = await axios.post(url_homepage, data);
    res.json(response.data);
  } catch (err) {
    console.error(err.message);
    res.status(500).json({ error: err.message });
  }
};