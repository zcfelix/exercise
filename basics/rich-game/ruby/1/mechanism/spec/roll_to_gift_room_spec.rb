require_relative '../lib/game/player.rb'
require_relative '../lib/command/roll_command.rb'
require_relative '../lib/game/map.rb'
require_relative '../lib/place/place.rb'
require_relative '../lib/place/land.rb'
require_relative '../lib/place/gift_room.rb'
require_relative '../lib/game/config.rb'


describe RollCommand do
  before(:each) do
    @step = 1
    @target = GiftRoom.new
    @map = Map.new
    @roll = RollCommand.new(@map, @step)
    allow(@map).to receive(:move) {@target}
    @player = Player.new :balance => 0, :points => 0
    @player.execute(@roll)
    expect(@player.status).to eq(:wait_for_choose_gift)
  end

  it 'should choose bonus money' do
    @player.respond(:choose_money)
    expect(@player.status).to eq(:end_turn)
    expect(@player.balance).to eq(GiftConf::BONUS_MONEY)
  end

  it 'should choose bonus points' do
    @player.respond(:choose_points)
    expect(@player.status).to eq(:end_turn)
    expect(@player.points).to eq(GiftConf::BONUS_POINTS)
  end

  it 'should choose mascot' do
    @player.respond(:choose_mascot)
    expect(@player.status).to eq(:end_turn)
    expect(@player.no_punish_turns).to eq(GiftConf::TURN_WITH_MASCOT)
  end

  it 'should end turn when player choose to exit' do
    @player.respond(:exit)
    expect(@player.status).to eq(:end_turn)
  end

  it 'should ensure effect of mascot' do
    @player.respond(:choose_mascot)
    expect(@player.status).to eq(:end_turn)
    expect(@player.no_punish_turns).to eq(GiftConf::TURN_WITH_MASCOT)

    other_player = Player.new :balance => 0
    other_land = Land.new(200)
    other_land.owner = other_player
    allow(@map).to receive(:move) {other_land}

    new_roll = RollCommand.new(@map, @step)
    @player.gain(2000)

    i = GiftConf::TURN_WITH_MASCOT
    GiftConf::TURN_WITH_MASCOT.times do
      @player.execute(new_roll)
      expect(@player.status).to eq(:end_turn)
      expect(@player.no_punish_turns).to eq(i - 1)
      expect(@player.balance).to eq(2000)
      expect(other_player.balance).to eq(0)
      i = @player.no_punish_turns
    end
    @player.execute(new_roll)
    expect(@player.balance).to eq(2000 - 200 / 2)
    expect(other_player.balance).to eq(200 / 2)
    expect(@player.status).to eq(:end_turn)
  end

end
