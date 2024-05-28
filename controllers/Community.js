import Community from "../models/CommunityModel.js";
import bcrypt from 'bcrypt';
import jwt from 'jsonwebtoken';

export const getCommunity = async (req, res) => {
    try {
        const community = await Community.findAll();
        res.json(community);
    } catch (error) {
        console.error(error);
    }
}
export const createCommunity = async (req, res) => {
    try {
        const { user_id, caption } = req.body;
        await Community.create({
            user_id,
            caption
        });
        res.json({msg: "Community created successfully"});
    } catch (error) {
        console.error(error);
    }
}

export const getCommunityById = async (req, res) => {
    try {
        const community = await Community.findAll({where: {id: req.params.id}});
        res.json(community);
    } catch (error) {
        console.error(error);
    }
}

